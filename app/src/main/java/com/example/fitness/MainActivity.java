package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitness.adapter.CategoryAdapter;
import com.example.fitness.adapter.CourseAdapter;
import com.example.fitness.model.Category;
import com.example.fitness.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullcourseList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Растяжка"));
        categoryList.add(new Category(2, "Силовые"));
        categoryList.add(new Category(3, "Кроссфит"));
        categoryList.add(new Category(4, "Прочее"));

        setCategoryRecycler(categoryList);

        //Йога для начинающих
        //программа для погружения
        //в йогу!
        courseList.add(new Course(1, "бодифлекс", "Дыхательная гимнастика\nв сочетании с упражнениями\nна растяжку.", "20 минут", "Групповое", "#424345", "Test", 1));
        courseList.add(new Course(2, "йога", "Йога для берменных.\nГотовят тело и разум\nк предстоящим родам.", "45 минут", "Индивидуальное", "#9FA52D", "Test", 1));
        courseList.add(new Course(3, "Кардио", "Core First — три блока\nпо 20 минут: кардионагрузка,\nсиловая и растяжка", "1 час", "Индивидуальное", "#6495ED", "Test", 3));
        courseList.add(new Course(4, "Сила", "Power - силовыe упражнения\nи движения взрывного\nхарактера.", "45 минут", "Индивидуальное", "#708090", "Test", 2));
        courseList.add(new Course(5, "Еда", "Полезно и вкусно! Индвидульный\n план здорового питания под\nваши нужды", "На следующий день", "Индивидуальное", "#FFA07A", "Test",4));

        fullcourseList.addAll(courseList);

        setCourseRecycler(courseList);
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);

    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);


    }

    public static void showCoursesByCategory(int category) {

        courseList.clear();
        courseList.addAll(fullcourseList);

        List<Course> filterCourses = new ArrayList<>();

        for(Course c : courseList) {
            if(c.getCategory() == category)
                filterCourses.add(c);

        }

        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();

    }
}