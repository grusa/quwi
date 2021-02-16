package earth.sochi.quwi.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.Dao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import earth.sochi.quwi.R;
import earth.sochi.quwi.data.Project;
import earth.sochi.quwi.data.ProjectDao;
import earth.sochi.quwi.databinding.FragmentProjectFormBinding;
import earth.sochi.quwi.databinding.FragmentProjectListBinding;
import earth.sochi.quwi.project.ProjectViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectFormFragment extends Fragment {


    private ProjectFormViewModel model;
    private FragmentProjectFormBinding binding;
    private Button ok,cancel;
    private EditText name,description;
    public ProjectFormFragment() {
        // Required empty public constructor
    }
    public static ProjectFormFragment newInstance(String param1, String param2) {
        ProjectFormFragment fragment = new ProjectFormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_form, container, false);

        binding = FragmentProjectFormBinding.bind(view);
        cancel = binding.cancelBt;
        ok = binding.okBt;
        name = binding.nameEt;
        description = binding.desriptionEt;
        model = new ViewModelProvider(this).get(ProjectFormViewModel.class);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Project p = new Project();
                p.name =name.getText().toString();
                p.description = description.getText().toString();
                if (model.insert(p)) {
                    Navigation.findNavController(view).navigate(R.id.action_projectFormFragment_to_ProjectListFragment);
                } else {
                    Toast.makeText(getContext(),"Can't insert data", Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_projectFormFragment_to_ProjectListFragment);
            }
        });
        return view;
    }
}