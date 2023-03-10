package com.example.mydietolog.data;

import android.provider.BaseColumns;


public final class Contants {

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

    public static final class ConstantsSQL {
        public final static String Id = "Id";

        public final static class CREATOR {
            public final static String TABLE_USER =
                    "CREATE TABLE IF NOT EXISTS "+User.TABLE+"(" + Id +
                            " INTEGER NOT NULL," + User.LOGIN +
                            " TEXT NOT NULL UNIQUE," + User.AGE +
                            " INTEGER NOT NULL," + User.WEIGHT +
                            " REAL NOT NULL," + User.HEIGHT +
                            " REAL NOT NULL," +
                            "PRIMARY KEY("+ Id +" AUTOINCREMENT)" +
                            ");";

            public final static String TABLE_EXERCISE = "CREATE TABLE IF NOT EXISTS " + Exercise.TABLE + "(" + Id +
                            " INTEGER NOT NULL," + Exercise.NAME +
                            " TEXT NOT NULL UNIQUE,"+ Exercise.DESCRIPTION +
                            " TEXT NOT NULL," + Exercise.SPENT_CALORIES +
                            " REAL NOT NULL," + Exercise.IMAGE +
                            " INTEGER NOT NULL," +
                            "PRIMARY KEY("+Id+" AUTOINCREMENT)" +
                            ");";
            public final static String TABLE_EXERCISE_USER = "CREATE TABLE IF NOT EXISTS "+ExerciseUser.TABLE+"(" +Id+
                            " INTEGER NOT NULL," + ExerciseUser.USERID +
                            " INTEGER NOT NULL REFERENCES "+User.TABLE+"("+Id+"), " + ExerciseUser.EXERCISEID +
                            " INTEGER NOT NULL REFERENCES "+Exercise.TABLE+"("+Id+"), " + ExerciseUser.NUMBER +
                            " INTEGER NOT NULL," +
                            "PRIMARY KEY("+Id+" AUTOINCREMENT)" +
                            ");";
        }

        public final static class Dropper {
            public final static String DROP_USERS =
                    String.format("DROP TABLE IF EXISTS {0}", User.TABLE);
            public final static String DROP_EXERCISE =
                    String.format("DROP TABLE IF EXISTS {0}", Exercise.TABLE);
            public final static String DROP_EXERCISE_USER =
                    String.format("DROP TABLE IF EXISTS {0}", ExerciseUser.TABLE);
        }

        public final static class User implements BaseColumns {
            public final static String TABLE = "User";
            public final static String LOGIN = "Login";
            public final static String AGE = "Age";
            public final static String WEIGHT = "Weight";
            public final static String HEIGHT = "Height";
        }

        public final static class Exercise implements BaseColumns {
            public final static String TABLE = "Exercise";
            public final static String IMAGE = "Image";
            public final static String NAME = "Name";
            public final static String DESCRIPTION = "Description";
            public final static String SPENT_CALORIES = "SpentCalories";
        }

        public final static class ExerciseUser implements BaseColumns {
            public final static String TABLE = "ExerciseUser";
            public final static String USERID = "UserID";
            public final static String EXERCISEID = "ExerciseID";
            public final static String NUMBER = "Number";
        }
    }
}
