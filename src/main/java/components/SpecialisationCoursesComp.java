package components;

import annotations.Component;
import annotations.Driver;
import exceptions.MonthException;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Lessons;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("//section/h2[text()='Специализации']/../div//div[h5]")
public class SpecialisationCoursesComp extends AnyComponentAbs<FavouriteCourses> {

    public SpecialisationCoursesComp(WebDriver driver) {
        super(driver);
    }
    @Driver
    public WebDriver driver;
    private final String searchCourseName = "Fullstack";
    @FindBy(xpath = "//section/h2[text()='Специализации']/../div//div[h5]")

    private List<WebElement> lessons;
    boolean flag;

     @FindBy(xpath = "//section/h2[text()='Специализации']/../div//div[h5]//following-sibling::div/span")
    private List<WebElement> datesFull;

         public List<String> gatherDateOfCard() {

       List<WebElement> elements = datesFull;
       List<String> dates = new ArrayList<>();
       for (int i = 0; i < elements.size(); i=i+1) {
           dates.add(elements.get(i).getText());

       }
       return dates;
   }


    private List<String> takeTexts(List<WebElement> list) {
        List<String> fullList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            fullList.add(list.get(i).getText());
        }
        return fullList;
    }

    public void filterLessonsItem() {
      List <String> courseName =   takeTexts(lessons).stream().
                filter(e -> e.contains(searchCourseName))
                .collect(Collectors.toList());
        for (int i = 0; i <courseName.size() ; i++) {


        Assertions.assertTrue(courseName.get(i).contains(searchCourseName), searchCourseName+ " Такой курс отсутсвует");}


    }

    public String convertMonthFromRussianToNumber(String month) throws MonthException {
        switch (month) {
            case "сентября": {
                return "09";
            }
            case "октября": {
                return "10";
            }
            case "ноября": {
                return "11";
            }
            case "декабря": {
                return "12";
            }
            case "января": {
                return "01";
            }
            case "февраля": {
                return "02";
            }
            case "марта": {
                return "03";
            }
            case "апреля": {
                return "04";
            }
            case "мая": {
                return "05";
            }
            case "июня": {
                return "06";
            }
            case "июля": {
                return "07";
            }
            case "августа": {
                return "08";
            }
            default:
                throw new MonthException(month);
        }
    }

    public String[] divideDateOnParts(int number) {
        List<String> dates = gatherDateOfCard();
        String str = dates.get(number);
        String[] fragments = str.split(" ");
        return fragments;


    }
    public List <String> swapDateParts() throws MonthException {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        List <String> convertDates = new ArrayList<>();
        for (int i = 0; i <gatherDateOfCard().size() ; i++) {
            String [] fragments = divideDateOnParts(i);
            if (fragments[1].equals("дате")) {fragments[1]="0";}
            else {
            if (Integer.parseInt(fragments[1])<10) {fragments[1]="0"+ fragments[0];}
            if (Integer.parseInt(fragments[3])>2000) {year = Integer.parseInt(fragments[3]);}
            convertDates.add(year + "-"+ convertMonthFromRussianToNumber(fragments[2])+ "-"

                    + fragments[1]);
        }}
        return convertDates;
    }
    public List <LocalDate> convertStringToDate() throws MonthException, ParseException {
        List <LocalDate> parsedDates = new ArrayList<>();
        for (int i = 0; i < swapDateParts().size() ; i++) {
            String dateStr = swapDateParts().get(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dateStr,formatter);
            parsedDates.add(startDate);
            }
        return parsedDates;
    }


     public LocalDate findMaxOrMin (boolean flag) throws ParseException, MonthException {
         List <LocalDate> parsedDates2 = convertStringToDate();
         Stream<LocalDate> stream = parsedDates2.stream();
         LocalDate constant;


        if (flag==true) {
            Optional<LocalDate> max = stream
                    .reduce((x,y)->x.isAfter(y)
                            ? x : y);
            LocalDate maximum = max.get();
            constant = maximum;
            return constant;
        } else {
            Optional<LocalDate> min = stream
                    .reduce((x,y)->x.isBefore(y)
                            ? x : y);;
            LocalDate minimum = min.get();
            constant = minimum;}

       return constant;
     }


     public void printMaxORMin() throws ParseException, MonthException {
         Random r = new Random();
         flag = r.nextBoolean();
        LocalDate parsedDates3 = findMaxOrMin(flag);
         if (flag == true)
         {System.out.println("Date of latest course is = "+ parsedDates3);}
         else {
         System.out.println("Date of earliest course is = "+ parsedDates3);}

         Assertions.assertTrue(parsedDates3.isAfter(LocalDate.now()), "Найден курс, который начинается ранее текущей даты");
     }



    }
