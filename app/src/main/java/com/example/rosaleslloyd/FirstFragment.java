package com.example.rosaleslloyd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.rosaleslloyd.databinding.FragmentFirstBinding;
import com.example.rosaleslloyd.model.User;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(v -> {
            EditText usernameField = getActivity().findViewById(R.id.username);
            EditText passwordField = getActivity().findViewById(R.id.password);

            String inputUsername = usernameField.getText().toString().trim();
            String inputPassword = passwordField.getText().toString().trim();

            User user = UserPrefManager.getInstance(getActivity()).getUser();

            if (user != null && inputUsername.equals(user.getUsername()) && inputPassword.equals(user.getPassword())) {
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonSecond.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RegisterForm.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}