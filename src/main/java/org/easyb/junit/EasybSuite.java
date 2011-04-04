package org.easyb.junit;

import org.junit.runner.RunWith;

import java.io.File;

@RunWith(EasybJUnitRunner.class) 
public abstract class EasybSuite {

    protected File baseDir() {
      return new File("spec");
   }

    protected boolean generateReports(){
       return false;
    }

    protected File reportsDir(){
       return new File("reports");
    }

   protected File searchDir() {
      String path = getClass().getName();
      path = path.substring(0, path.lastIndexOf('.'));
      path = path.replace('.', '/');
      return new File(baseDir(), path);
   }

   protected String description() {
      return getClass().getName();
   }

   protected boolean trackTime() {
      return false;
   }
}
