package up.visulog.gitrawdata;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import java.util.*;

import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.AnyObjectId;

import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;

import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

public class Commit {
    // AD: FIXME: (some of) these fields could have more specialized types than String
    public boolean noError = true;
    public final String id;
    public final String date;
    public final String author;
    public final String description;
    public LinkedHashMap<String , Integer> authorlist = new LinkedHashMap<String , Integer>();
    public String brancheN;

    public Commit(String id, String author, String date, String description) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.description = description;
    }

    public void getrepo(){
      System.out.println(System.getProperty("user.dir"));
    }
    public void CloneRep(String s){
        String repoUrl = s;
        String cloneDirectoryPath = "datagit";
        try {
            Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(Paths.get(cloneDirectoryPath).toFile())
                    .call();
        } catch (GitAPIException e) {
            noError = false;
        }
    }

    public void getCommit(String str) throws IOException, GitAPIException {
      Repository repo = new FileRepository("datagit/.git");
      Git git = new Git(repo);
      RevWalk walk = new RevWalk(repo);

        List<Ref> branches = null;
        try {
            branches = git.branchList().call();
        } catch (GitAPIException e) {
            System.out.println("Erreur");
        }

        for (Ref branch : branches) {
          String branchName = branch.getName();

          Iterable<RevCommit> commits = null;
          try {
              if(str.equals("all")){
                commits = git.log().all().call();
              }else{
                commits = git.log()
                .add(repo.resolve("remotes/origin/"+str))
                .call();
            }
                brancheN = str;
          } catch (GitAPIException e) {
              System.out.println("Erreur");
          }

          for (RevCommit commit : commits) {
             
              RevCommit targetCommit = walk.parseCommit(repo.resolve(
                      commit.getName()));
              for (Map.Entry<String, Ref> e : repo.getAllRefs().entrySet()) {
                  if (e.getKey().startsWith(Constants.R_HEADS)) {
                      if (walk.isMergedInto(targetCommit, walk.parseCommit(
                              e.getValue().getObjectId()))) {
                          String foundInBranch = e.getValue().getName();
                          
                      }
                  }
              }

              
                      if(authorlist.containsKey(commit.getAuthorIdent().getName())){
                          authorlist.replace(commit.getAuthorIdent().getName(),authorlist.get(commit.getAuthorIdent().getName()),authorlist.get(commit.getAuthorIdent().getName())+1);
                          //System.out.println("Nombre de Comits de : "+ commit.getAuthorIdent().getName() + " : " + authorlist.get(commit.getAuthorIdent().getName()));

                      }
                      else {
                          authorlist.put(commit.getAuthorIdent().getName(),1);
                      }
                  //System.out.println(commit.getName());
                  //System.out.println(commit.getAuthorIdent().getName());
                  //System.out.println(new Date(commit.getCommitTime() * 1000L));
                  //System.out.println(commit.getFullMessage());
              
          }
      }
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Transforms a time encoded as long into a string with
     * the git log format.
     */
    static String stringOfTime(long time, TimeZone tz) {
	var dtfmt =
	    new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.US);
	dtfmt.setTimeZone(tz);
	dtfmt.format(Long.valueOf(time));
	return dtfmt.format(Long.valueOf(time));
    }

    /**
     * Transform a JGit revCommit into a regular Commit object.
     */
    public static Commit commitOfRevCommit (AnyObjectId id, RevCommit rCommit){
	var  author = rCommit.getAuthorIdent();
	var name = author.getName();
	var email = author.getEmailAddress();
	var time = author.getWhen().getTime();
	var timeZone = author.getTimeZone();
	var commit =
	    new Commit(id.getName(),
		       name + " <" + email+">",
		       stringOfTime(time, timeZone),
		       rCommit.getFullMessage());
	return commit;
    }


    /**
     * Parses a log item and outputs a commit object. Exceptions will
     * be thrown in case the input does not have the proper format.
     */
    public static Commit parse (Repository repo, AnyObjectId id)
	throws MissingObjectException,
	       IncorrectObjectTypeException,
	       IOException {
	try (RevWalk walk = new RevWalk(repo)) {
	    RevCommit rCommit = walk.parseCommit(id);
	    walk.dispose();
	    return commitOfRevCommit(id, rCommit);
	}
    }

    public void printCommit(){
        System.out.println("Commits of branch: " + brancheN +" :");
        System.out.println("-------------------------------------");
        for (Map.Entry mapentry : authorlist.entrySet()) {
            System.out.println("Nombre de Comits de : "+mapentry.getKey() + " : " + mapentry.getValue());
        }
    }
    public LinkedHashMap<String , Integer> gethmap(){
        return authorlist;
    }


}
