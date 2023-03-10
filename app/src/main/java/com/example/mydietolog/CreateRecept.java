package com.example.mydietolog;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mydietolog.databinding.FragmentCreateReceptBinding;
import com.example.mydietolog.databinding.FragmentNotificationsBinding;
import com.example.mydietolog.ui.notifications.NotificationsViewModel;

public class CreateRecept extends Fragment {

    private CreateReceptViewModel mViewModel;
    private FragmentCreateReceptBinding binding;

    public static CreateRecept newInstance() {
        return new CreateRecept();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreateReceptViewModel createReceptViewModel =
                new ViewModelProvider(this).get(CreateReceptViewModel.class);

        binding = FragmentCreateReceptBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button addIngredient = binding.addIngredientField;
        final LinearLayout ingredientTemplate = binding.templateIngredient;
        final LinearLayout containerIngredient = binding.containerIngredients;

        addIngredient.setOnClickListener(view -> {
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);

            for(int i = 0; i < ingredientTemplate.getChildCount(); i++){
                EditText edText = new EditText(getContext());
                EditText edTemplate = (EditText) ingredientTemplate.getChildAt(i);

                edText.setWidth(edTemplate.getWidth());
                edText.setHeight(edTemplate.getHeight());
                edText.setHint(edTemplate.getHint());

                layout.addView(edText);
            }

            containerIngredient.addView(layout);
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CreateReceptViewModel.class);
    }

}