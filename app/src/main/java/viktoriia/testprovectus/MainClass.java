package viktoriia.testprovectus;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainClass  extends Application {

    private static Example example;
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        example = retrofit.create(Example.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static Example getApi() {
        return example;
    }

}
