package ui.courses;

import annotations.Driver;
import components.SpecialisationCoursesComp;
import exceptions.MonthException;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.text.ParseException;

@ExtendWith(UIExtension.class)
public class SpecialisationCourse_Test {
    @Driver
    public WebDriver driver;

    @Test
    public void filterCourses(){
        new MainPage(driver).open();

        new SpecialisationCoursesComp(driver).filterLessonsItem();
    }

    @Test
    public void chooseCourseStartingEarlyOrLater() throws ParseException, MonthException {
        new MainPage(driver).open();
        new SpecialisationCoursesComp(driver).printMaxORMin();

    }


}


