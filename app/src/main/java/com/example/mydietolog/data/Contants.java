package com.example.mydietolog.data;


import com.example.mydietolog.model.User;

public final class Contants {

    public static com.example.mydietolog.model.User CurrentUser;

    static {
        try {
            CurrentUser = new com.example.mydietolog.model.User();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final class User{
        public static final String TABLE = "Users";
        public static final String EMAIL = "Email";
        public static final String PASSWORD = "Password";
        public static final String AGE = "Age";
        public static final String WEIGHT = "Weight";
        public static final String HEIGHT = "Height";
        public static final String SPENT_CALORIES = "SpentCalories";
        public static final String EXERCISES = "Exercises";
    }
    public static final class Notifications{
        public static final String COMPLETE_AUTH = "Успешная авторизация";
        public static final String NOT_COMPLETE_AUTH = "Неверный логин или пароль";
        public static final String REGISTER_PROBLEM_LOGIN = "Логин был меньше 4 символов";
        public static final String REGISTER_PROBLEM_EMAIL = "Почта введена не верно";
        public static final String REGISTER_PROBLEM_PASS = "Пароль слишком маленький";
        public static final String REGISTER_PROBLEM_PASSREPEAT = "Пароли не совпадают";
        public static final String REGISTER_PROBLEM_AGE = "Возраст был введен некорректно";
        public static final String REGISTER_PROBLEM_WEIGHT = "Вес был введен некорректно";
        public static final String REGISTER_PROBLEM_HEIGHT = "Рост был введен некорректно";
    }

    public static final class LocalData{
        public static final String USER_LOGIN = "user_login";
    }

    public static final class Exercise{
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";
        public static final String SPENT_CALORIES = "spentCalories";
    }

    public static final class Storage{
        public static final String ICONS_RECEPTS = "receptIcons";
        public static final String IMAGE_NOT_FOUND = ICONS_RECEPTS + "/notfound.jpg";
    }

    public static final class Recept{
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String INGRIDIENTS = "Ingridients";
        public static final String STEPS = "Steps";
    }

    public static final class SQL {
        public static final String TABLE_EXERCISE = "Exercise";
        public static final String TABLE_EXERCISE_USER = "ExerciseUser";
        public static final String TABLE_USER = "User";
    }
}
