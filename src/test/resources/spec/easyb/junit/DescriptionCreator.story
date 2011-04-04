package spec.easyb.junit

import org.easyb.domain.Behavior
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

scenario "a description is created", {
   given "a description creator with root directory of spec and a behavior underneath",{
      descriptionCreator = new org.easyb.junit.DescriptionCreator(new File('spec'))
      file = new File(new File('spec'), 'spec/easyb/junit/RunNotifierReplay.specification')
      behavior = mock(Behavior.class)
      when(behavior.getFile()).thenReturn(file)
   }
   
   when "create is called", {
      description = descriptionCreator.create(behavior)
   }
   
   then "the display should be the fully qualified class name", {
      description.getDisplayName().shouldBe 'spec.easyb.junit.RunNotifierReplay'
   }
   
   and "the extension is taken off", {
      description.getDisplayName().shouldBe 'spec.easyb.junit.RunNotifierReplay'
   }
}

scenario "a description is created duplicate", {
   given "a description creator with root directory of spec and a behavior underneath",{
      descriptionCreator = new org.easyb.junit.DescriptionCreator(new File('spec'))
      file = new File(new File('spec'), 'spec/easyb/junit/RunNotifierReplay.specification')
      behavior = mock(Behavior.class)
      when(behavior.getFile()).thenReturn(file)
   }
   
   when "create is called", {
      description = descriptionCreator.create(behavior)
   }
   
   then "the display should be the fully qualified class name", {
      description.getDisplayName().shouldBe 'spec.easyb.junit.RunNotifierReplay'
   }
   
   and "the extension is taken off", {
      description.getDisplayName().shouldBe 'spec.easyb.junit.RunNotifierReplay'
   }
}