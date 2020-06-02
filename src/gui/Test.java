package gui;

import java.util.ArrayList;

import Util.Result;
import Util.Util;

public class Test {

	public static void main(String[] args) {
		Util util = new Util();
		String file ="package org.springframework.boot.build;\r\n" + 
				"\r\n" + 
				"import java.io.File;\r\n" + 
				"import java.net.URI;\r\n" + 
				"import java.util.Collections;\r\n" + 
				"import java.util.HashMap;\r\n" + 
				"import java.util.Map;\r\n" + 
				"\r\n" + 
				"import org.asciidoctor.gradle.jvm.AbstractAsciidoctorTask;\r\n" + 
				"import org.asciidoctor.gradle.jvm.AsciidoctorJExtension;\r\n" + 
				"import org.asciidoctor.gradle.jvm.AsciidoctorJPlugin;\r\n" + 
				"import org.asciidoctor.gradle.jvm.AsciidoctorTask;\r\n" + 
				"import org.gradle.api.Action;\r\n" + 
				"import org.gradle.api.DefaultTask;\r\n" + 
				"import org.gradle.api.Project;\r\n" + 
				"import org.gradle.api.Task;\r\n" + 
				"import org.gradle.api.artifacts.Configuration;\r\n" + 
				"import org.gradle.api.file.FileCollection;\r\n" + 
				"import org.gradle.api.tasks.InputFiles;\r\n" + 
				"import org.gradle.api.tasks.OutputDirectory;\r\n" + 
				"import org.gradle.api.tasks.Sync;\r\n" + 
				"import org.gradle.api.tasks.TaskAction;\r\n" + 
				"\r\n" + 
				"import org.springframework.boot.build.artifactory.ArtifactoryRepository;\r\n" + 
				"import org.springframework.util.StringUtils;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * Conventions that are applied in the presence of the {@link AsciidoctorJPlugin}. When\r\n" + 
				" * the plugin is applied:\r\n" + 
				" *\r\n" + 
				" * <ul>\r\n" + 
				" * <li>All warnings are made fatal.\r\n" + 
				" * <li>A task is created to resolve and unzip our documentation resources (CSS and\r\n" + 
				" * Javascript).\r\n" + 
				" * <li>For each {@link AsciidoctorTask} (HTML only):\r\n" + 
				" * <ul>\r\n" + 
				" * <li>A task is created to sync the documentation resources to its output directory.\r\n" + 
				" * <li>{@code doctype} {@link AsciidoctorTask#options(Map) option} is configured.\r\n" + 
				" * <li>{@link AsciidoctorTask#attributes(Map) Attributes} are configured for syntax\r\n" + 
				" * highlighting, CSS styling, docinfo, etc.\r\n" + 
				" * </ul>\r\n" + 
				" * <li>For each {@link AbstractAsciidoctorTask} (HTML and PDF):\r\n" + 
				" * <ul>\r\n" + 
				" * <li>{@link AsciidoctorTask#attributes(Map) Attributes} are configured to enable\r\n" + 
				" * warnings for references to missing attributes, the GitHub tag, the Artifactory repo for\r\n" + 
				" * the current version, etc.\r\n" + 
				" * <li>{@link AbstractAsciidoctorTask#baseDirFollowsSourceDir() baseDirFollowsSourceDir()}\r\n" + 
				" * is enabled.\r\n" + 
				" * </ul>\r\n" + 
				" * </ul>\r\n" + 
				" *\r\n" + 
				" * @author Andy Wilkinson\r\n" + 
				" */\r\n" + 
				"class AsciidoctorConventions {\r\n" + 
				"\r\n" + 
				"	void apply(Project project) {\r\n" + 
				"		project.getPlugins().withType(AsciidoctorJPlugin.class, (asciidoctorPlugin) -> {\r\n" + 
				"			configureDocResourcesRepository(project);\r\n" + 
				"			makeAllWarningsFatal(project);\r\n" + 
				"			UnzipDocumentationResources unzipResources = createUnzipDocumentationResourcesTask(project);\r\n" + 
				"			project.getTasks().withType(AbstractAsciidoctorTask.class, (asciidoctorTask) -> {\r\n" + 
				"				configureCommonAttributes(project, asciidoctorTask);\r\n" + 
				"				configureOptions(asciidoctorTask);\r\n" + 
				"				asciidoctorTask.baseDirFollowsSourceDir();\r\n" + 
				"				Sync syncSource = createSyncDocumentationSourceTask(project, asciidoctorTask);\r\n" + 
				"				if (asciidoctorTask instanceof AsciidoctorTask) {\r\n" + 
				"					configureHtmlOnlyAttributes(asciidoctorTask);\r\n" + 
				"					syncSource.from(unzipResources, (resources) -> resources.into(\"asciidoc\"));\r\n" + 
				"					asciidoctorTask.doFirst(new Action<Task>() {\r\n" + 
				"\r\n" + 
				"						@Override\r\n" + 
				"						public void execute(Task task) {\r\n" + 
				"							project.copy((spec) -> {\r\n" + 
				"								spec.from(asciidoctorTask.getSourceDir());\r\n" + 
				"								spec.into(asciidoctorTask.getOutputDir());\r\n" + 
				"								spec.include(\"css/**\", \"js/**\");\r\n" + 
				"							});\r\n" + 
				"						}\r\n" + 
				"\r\n" + 
				"					});\r\n" + 
				"				}\r\n" + 
				"			});\r\n" + 
				"		});\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private void configureDocResourcesRepository(Project project) {\r\n" + 
				"		project.getRepositories().maven((mavenRepo) -> {\r\n" + 
				"			mavenRepo.setUrl(URI.create(\"https://repo.spring.io/release\"));\r\n" + 
				"			mavenRepo.mavenContent((mavenContent) -> mavenContent.includeGroup(\"io.spring.docresources\"));\r\n" + 
				"		});\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private void makeAllWarningsFatal(Project project) {\r\n" + 
				"		project.getExtensions().getByType(AsciidoctorJExtension.class).fatalWarnings(\".*\");\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private UnzipDocumentationResources createUnzipDocumentationResourcesTask(Project project) {\r\n" + 
				"		Configuration documentationResources = project.getConfigurations().maybeCreate(\"documentationResources\");\r\n" + 
				"		documentationResources.getDependencies()\r\n" + 
				"				.add(project.getDependencies().create(\"io.spring.docresources:spring-doc-resources:0.2.2.RELEASE\"));\r\n" + 
				"		UnzipDocumentationResources unzipResources = project.getTasks().create(\"unzipDocumentationResources\",\r\n" + 
				"				UnzipDocumentationResources.class);\r\n" + 
				"		unzipResources.setResources(documentationResources);\r\n" + 
				"		unzipResources.setOutputDir(new File(project.getBuildDir(), \"docs/resources\"));\r\n" + 
				"		return unzipResources;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private Sync createSyncDocumentationSourceTask(Project project, AbstractAsciidoctorTask asciidoctorTask) {\r\n" + 
				"		Sync syncDocumentationSource = project.getTasks()\r\n" + 
				"				.create(\"syncDocumentationSourceFor\" + StringUtils.capitalize(asciidoctorTask.getName()), Sync.class);\r\n" + 
				"		File syncedSource = new File(project.getBuildDir(), \"docs/src/\" + asciidoctorTask.getName());\r\n" + 
				"		syncDocumentationSource.setDestinationDir(syncedSource);\r\n" + 
				"		syncDocumentationSource.from(\"src/docs/\");\r\n" + 
				"		asciidoctorTask.dependsOn(syncDocumentationSource);\r\n" + 
				"		asciidoctorTask.setSourceDir(project.relativePath(new File(syncedSource, \"asciidoc/\")));\r\n" + 
				"		return syncDocumentationSource;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private void configureOptions(AbstractAsciidoctorTask asciidoctorTask) {\r\n" + 
				"		asciidoctorTask.options(Collections.singletonMap(\"doctype\", \"book\"));\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private void configureHtmlOnlyAttributes(AbstractAsciidoctorTask asciidoctorTask) {\r\n" + 
				"		Map<String, Object> attributes = new HashMap<>();\r\n" + 
				"		attributes.put(\"highlightjsdir\", \"js/highlight\");\r\n" + 
				"		attributes.put(\"highlightjs-theme\", \"github\");\r\n" + 
				"		attributes.put(\"linkcss\", true);\r\n" + 
				"		attributes.put(\"icons\", \"font\");\r\n" + 
				"		attributes.put(\"stylesheet\", \"css/spring.css\");\r\n" + 
				"		asciidoctorTask.attributes(attributes);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private void configureCommonAttributes(Project project, AbstractAsciidoctorTask asciidoctorTask) {\r\n" + 
				"		Map<String, Object> attributes = new HashMap<>();\r\n" + 
				"		attributes.put(\"attribute-missing\", \"warn\");\r\n" + 
				"		attributes.put(\"github-tag\", determineGitHubTag(project));\r\n" + 
				"		attributes.put(\"spring-boot-artifactory-repo\", ArtifactoryRepository.forProject(project));\r\n" + 
				"		attributes.put(\"version\", \"{gradle-project-version}\");\r\n" + 
				"		asciidoctorTask.attributes(attributes);\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	private String determineGitHubTag(Project project) {\r\n" + 
				"		String version = \"v\" + project.getVersion();\r\n" + 
				"		return (version.endsWith(\"-SNAPSHOT\")) ? \"master\" : version;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	/**\r\n" + 
				"	 * {@link Task} for unzipping the documentation resources.\r\n" + 
				"	 */\r\n" + 
				"	public static class UnzipDocumentationResources extends DefaultTask {\r\n" + 
				"\r\n" + 
				"		private FileCollection resources;\r\n" + 
				"\r\n" + 
				"		private File outputDir;\r\n" + 
				"\r\n" + 
				"		@InputFiles\r\n" + 
				"		public FileCollection getResources() {\r\n" + 
				"			return this.resources;\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		public void setResources(FileCollection resources) {\r\n" + 
				"			this.resources = resources;\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		@OutputDirectory\r\n" + 
				"		public File getOutputDir() {\r\n" + 
				"			return this.outputDir;\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		public void setOutputDir(File outputDir) {\r\n" + 
				"			this.outputDir = outputDir;\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"		@TaskAction\r\n" + 
				"		void syncDocumentationResources() {\r\n" + 
				"			getProject().sync((copySpec) -> {\r\n" + 
				"				copySpec.into(this.outputDir);\r\n" + 
				"				for (File resource : this.resources) {\r\n" + 
				"					copySpec.from(getProject().zipTree(resource));\r\n" + 
				"				}\r\n" + 
				"			});\r\n" + 
				"		}\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
		Result result= util.Split(file);
		util.Filter(result.words);
//		int i=0;
//		System.out.println("BlockCMT");
//		 for (String w : result.blockCmts) {
//			   w=w.trim();
//			   System.out.println(i+" "+w);
//			   i++;
//		  }
//		 i=0;
//			System.out.println("lineCmts");
//		
//		  for (String w : result.lineCmts) {
//			   w=w.trim();
//			   System.out.println(i+" "+w);
//			   i++;
//		  }
//		  i=0;
//			System.out.println("words");
//		  for (String w : result.words) {
//			   w=w.trim();
//			   System.out.println(i+" "+w);
//			   i++;
//		  }
//		  i=0;
//			System.out.println("str");
//		  for (String w : result.strs) {
//			   w=w.trim();
//			   System.out.println(i+" "+w);
//			   i++;
//		  }
	}
}
