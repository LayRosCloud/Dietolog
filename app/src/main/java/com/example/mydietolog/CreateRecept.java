package com.example.mydietolog;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;
import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mydietolog.data.Contants;
import com.example.mydietolog.data.DatabaseHelper;
import com.example.mydietolog.databinding.FragmentCreateReceptBinding;
import com.example.mydietolog.model.Category;
import com.example.mydietolog.model.StepIcon;
import com.example.mydietolog.model.Unit;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateRecept extends Fragment {

    interface Completed{
        void onComplete(Uri uri) throws IOException;
    }

    private CreateReceptViewModel mViewModel;
    private FragmentCreateReceptBinding binding;
    private ImageButton imageButton;

    private Uri imageIcon;

    private LinearLayout containerSteps;
    private LinearLayout containerIngredient;

    private final List<StepIcon> iconsForStep = new ArrayList<>();

    int stepCount = 2;
    int stepImageCount = 3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreateReceptViewModel createReceptViewModel =
                new ViewModelProvider(this).get(CreateReceptViewModel.class);

        binding = FragmentCreateReceptBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button addIngredient = binding.addIngredientField;
        final Button addStep = binding.addStepTemplate;
        final Button addReciepe = binding.addRecipe;
        final Button chooseImage = binding.chooseImageStep;

        imageButton = binding.imageButton;

        final LinearLayout ingredientTemplate = binding.templateIngredient;
        containerIngredient = binding.containerIngredients;

        final Spinner spinCategory = binding.categorySpin;

        containerSteps = binding.containerSteps;
        final LinearLayout stepTemplate = binding.templateStep;

        final EditText title = binding.titleRecept;
        final EditText description = binding.descriptionRecept;
        final EditText calories = binding.caloriesOfRecept;
        final EditText fats = binding.fatsOfRecept;
        final EditText carbohydrates = binding.carbohydratesOfRecept;
        final EditText protein = binding.proteinOfRecept;

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Spinner spinnerTemplate = binding.spinUnit;

        setAdapter(spinnerTemplate, Unit.findNames(db));
        setAdapter(spinCategory, Category.getNames(db));

        addIngredient.setOnClickListener(view -> {
            createIngredient(ingredientTemplate, containerIngredient, spinnerTemplate);
        });

        imageButton.setOnClickListener(view -> {
            getImage(1);
        });

        addStep.setOnClickListener(view -> {
            createStep(stepTemplate, containerSteps);
        });

        addReciepe.setOnClickListener(view -> {

            Cursor cursor1 = db.rawQuery("SELECT _id FROM Category WHERE Name LIKE \'"
                    + spinCategory.getSelectedItem().toString() + "\'", null);
            cursor1.moveToNext();

            int categoryId = cursor1.getInt(0);

            save(title.getText().toString(),
                    description.getText().toString(),
                    Double.parseDouble(calories.getText().toString()),
                    Double.parseDouble(carbohydrates.getText().toString()),
                    Double.parseDouble(fats.getText().toString()),
                    Double.parseDouble(protein.getText().toString()),
                    categoryId);
        });

        chooseImage.setOnClickListener(view -> {
            getImage(2);
        });

        return root;
    }

    private void getImage(int code){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null && data.getData() != null){
            if(resultCode == RESULT_OK){
                imageButton.setImageURI(data.getData());
                imageIcon = data.getData();
            }
        }
        else if(data != null && data.getData() != null){
            if(resultCode == RESULT_OK){
                int i = 0;

                for(; i <= iconsForStep.size(); i++){
                    if(i == iconsForStep.size()){
                        i = -1;
                        break;
                    }
                    if(iconsForStep.get(i).getId() == requestCode){
                        break;
                    }
                }

                if(i == -1){
                    StepIcon stepIcon = new StepIcon(requestCode, data.getData());
                    iconsForStep.add(stepIcon);
                    return;
                }

                iconsForStep.get(i).setUri(data.getData());
            }
        }
    }

    private void uploadPreview(Completed onComplete){
        Bitmap bitmap = ((BitmapDrawable) imageButton.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] byteArray = baos.toByteArray();

        final StorageReference storage = FirebaseStorage.getInstance().getReference("Recepts")
                .child("preview").child(System.currentTimeMillis() + "_icon");
        UploadTask up = storage.putBytes(byteArray);
        Task<Uri> taskUploading = up.continueWithTask(task -> storage.getDownloadUrl())
                .addOnCompleteListener(task -> {
                    try {
                        onComplete.onComplete(task.getResult());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void uploadIconStep(Uri uri,Completed onComplete) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] byteArray = baos.toByteArray();

        final StorageReference storage = FirebaseStorage.getInstance().getReference("Recepts")
                .child("iconsSteps").child(System.currentTimeMillis() + "_image");
        UploadTask up = storage.putBytes(byteArray);
        Task<Uri> taskUploading = up.continueWithTask(task -> storage.getDownloadUrl())
                .addOnCompleteListener(task -> {
                    try {
                        onComplete.onComplete(task.getResult());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void createStep(LinearLayout template, LinearLayout container){
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView titleTemplate = (TextView)template.getChildAt(0);
        TextView title = new TextView(getContext());
        title.setText("Шаг " + stepCount++);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            title.setTextSize(titleTemplate.getTextSizeUnit(), titleTemplate.getTextSize());
        }
        else{
            title.setTextSize(titleTemplate.getTextSize());
        }
        copyView(titleTemplate, title);

        Button button = new Button(getContext());
        Button buttonTemplate = (Button) template.getChildAt(1);
        button.setText(buttonTemplate.getText());
        button.setTextColor(buttonTemplate.getTextColors());
        button.setOnClickListener(view -> {
            getImage(stepImageCount++);
        });
        copyView(buttonTemplate, button);

        EditText templateName = (EditText) template.getChildAt(2);
        EditText name = new EditText(getContext());
        copyEditText(templateName, name);

        EditText descriptionTemplate = (EditText) template.getChildAt(3);
        EditText description = new EditText(getContext());
        copyEditText(descriptionTemplate, description);

        layout.addView(title); //0
        layout.addView(button); // 1
        layout.addView(name); // 2
        layout.addView(description);

        container.addView(layout);
    }

    private void createIngredient(LinearLayout template, LinearLayout container, Spinner spinnerTemplate){
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);

        for(int i = 0; i < template.getChildCount() - 1; i++){
            EditText edText = new EditText(getContext());
            EditText edTemplate = (EditText) template.getChildAt(i);

            edText.setWidth(edTemplate.getWidth());
            edText.setHeight(edTemplate.getHeight());
            edText.setHint(edTemplate.getHint());

            layout.addView(edText);
        }
        Spinner sp = new Spinner(getContext());
        sp.setMinimumWidth(spinnerTemplate.getMinimumWidth());
        sp.setMinimumHeight(spinnerTemplate.getMinimumHeight());
        sp.setAdapter(spinnerTemplate.getAdapter());

        layout.addView(sp);

        container.addView(layout);
    }

    private void save( String title,
                       String description,
                       double calories,
                       double carbohydrates,
                       double fats,
                       double protein,
                       int category){
        uploadPreview((uri) -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            int receptID = generateRecept(title, description, calories, carbohydrates, fats, protein, category, db, uri);

            for (int i = 0; i < containerIngredient.getChildCount(); i++){
                LinearLayout linear = (LinearLayout) containerIngredient.getChildAt(i);

                EditText name = (EditText) linear.getChildAt(0);
                EditText count = (EditText) linear.getChildAt(1);
                Spinner unit = (Spinner) linear.getChildAt(2);

                String ingredientName = name.getText().toString();
                double countOf = Double.parseDouble(count.getText().toString());

                Cursor cursor1 = db.rawQuery("SELECT _id FROM Unit WHERE Name LIKE \'"
                        + unit.getSelectedItem().toString() + "\'", null);
                cursor1.moveToNext();

                int unitId = cursor1.getInt(0);

                ContentValues cvOfIngredient = new ContentValues();
                cvOfIngredient.put("ReceptID", receptID);
                cvOfIngredient.put("Name", ingredientName);
                cvOfIngredient.put("Number", countOf);
                cvOfIngredient.put("UnitID", unitId);

                db.insert("ReceptsIngredient", null, cvOfIngredient);
            }



            for (int i = 0; i < containerSteps.getChildCount(); i++){
                LinearLayout linear = (LinearLayout) containerSteps.getChildAt(i);

                EditText titleStep = (EditText) linear.getChildAt(2);
                EditText descriptionStep = (EditText) linear.getChildAt(3);

                ContentValues cvSteps = new ContentValues();
                cvSteps.put("Title", titleStep.getText().toString());
                cvSteps.put("Description", descriptionStep.getText().toString());

                for(StepIcon step: iconsForStep){
                    if(step.getId() - 2 == i){
                        uploadIconStep(step.getUri(), uri1 -> {
                            cvSteps.put("ImageIcon", uri1.toString());
                            db.insert("ReceptsSteps", null, cvSteps);
                        });

                        break;
                    }
                }
                db.insert("ReceptsSteps", null, cvSteps);
            }
        });

    }

    private int generateRecept(String title,
                               String description,
                               double calories,
                               double carbohydrates,
                               double fats,
                               double protein,
                               int category,
                               SQLiteDatabase db,
                               Uri uri){
        ContentValues cv = new ContentValues();
        cv.put("Title", title);
        cv.put("Description", description);
        cv.put("Calories", calories);
        cv.put("Carbohydrates", carbohydrates);
        cv.put("Fats", fats);
        cv.put("Protein", protein);
        cv.put("ImageIcon", uri.toString());
        cv.put("Author", Contants.CurrentUser.getLogin());
        cv.put("CategoryID", category);

        db.insert("Recepts", null, cv);

        Cursor cursor = db.rawQuery("SELECT _id FROM Recepts", null);
        cursor.moveToLast();
        return cursor.getInt(0);
    }

    private void copyView(final View template, final View obj){
        obj.setMinimumWidth(template.getWidth());
        obj.setMinimumHeight(template.getHeight());
        obj.setBackground(template.getBackground());
        obj.setForeground(template.getForeground());
    }
    private void copyEditText(final EditText template,final EditText obj){
        copyView(template, obj);
        obj.setTextColor(template.getTextColors());
        obj.setHint(template.getHint());
    }
    private void setAdapter(Spinner spinner, List<String> list){
        ArrayAdapter<String> autoBaseAdapter;
        autoBaseAdapter = new ArrayAdapter<String>(getContext(), simple_spinner_item, list);

        autoBaseAdapter.setDropDownViewResource(simple_spinner_dropdown_item);

        spinner.setAdapter(autoBaseAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreateReceptViewModel.class);
    }

}