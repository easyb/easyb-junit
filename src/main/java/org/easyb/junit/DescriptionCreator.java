package org.easyb.junit;

import org.easyb.domain.Behavior;
import org.junit.runner.Description;

import java.io.File;

import static org.junit.runner.Description.createSuiteDescription;

public class DescriptionCreator {
   private final String basePath;

   public DescriptionCreator(File baseDir) {
      this.basePath = baseDir.getAbsolutePath();
   }

   public Description create(Behavior behavior) {
      String name = replaceFileSeparatorsWithDots(getPathRelativeToBaseDir(behavior));
      return createSuiteDescription(name.substring(0, name.lastIndexOf('.')));
   }

   private String getPathRelativeToBaseDir(Behavior behavior) {
      return behavior.getFile().getAbsolutePath().substring(basePath.length() + 1);
   }

   private String replaceFileSeparatorsWithDots(String relPath) {
      return relPath.replace(System.getProperty("file.separator").charAt(0), '.');
   }
}
