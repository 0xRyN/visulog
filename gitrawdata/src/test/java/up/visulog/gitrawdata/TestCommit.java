package up.visulog.gitrawdata;

import java.io.IOException;

import org.junit.Test;

import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import static org.junit.Assert.assertEquals;

public class TestCommit {
    @Test
    public void testParse() throws IOException {
	var expected =
	    "Commit{id='6304c1acdc1cbdeb8315528781896abc72a021b8', date='Tue Sep 1 12:30:53 2020 +0200', author='Aldric Degorre <adegorre@irif.fr>', description='More gradle configuration (with subprojects)\n'}";
	var builder = new FileRepositoryBuilder();
	var repo = builder
	    .readEnvironment() // scan environment GIT_* variables
	    .findGitDir() // scan up the file system tree
	    .build();
	var id = repo.resolve("6304c1acdc");
        var commit = Commit.parse(repo, id);
	System.out.println("Hello world");
	assertEquals(expected, commit.toString());
    }
}
