plugins {
	java
	application
}

application.mainClass.set("up.visulog.cli.CliApplication")

dependencies {
    implementation("org.eclipse.jgit:org.eclipse.jgit:5.8.1.202007141445-r")
    implementation(project(":analyzer"))
    implementation(project(":config"))
    implementation("org.slf4j:slf4j-nop:1.7.30")
    implementation(project(":gitrawdata"))
    testImplementation("junit:junit:4.+")
	
}


