package earth.sochi.quwi.project;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;
import java.util.zip.Inflater;

import earth.sochi.quwi.R;
import earth.sochi.quwi.data.Project;
import earth.sochi.quwi.databinding.FragmentProjectListBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectListFragment extends Fragment {

    private String[] genDataSet;
    private FragmentProjectListBinding binding;
    private ProjectViewModel model;
    private Button buttonAdd ;
    private RecyclerView recyclerView;

    public static ProjectListFragment newInstance(String param1, String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_project_list,container,false);
        binding = FragmentProjectListBinding.bind(view);
        buttonAdd  = binding.buttonAdd;
        ProjectAdapter projectAdapter = new ProjectAdapter(getContext());
        recyclerView = binding.projectsRv;
        //container.findViewById(R.id.projectsRv);
        //        (RecyclerView) view.findViewById(R.id.projectsRv);
        recyclerView.setAdapter(projectAdapter);
        model = new ViewModelProvider(this).get(ProjectViewModel.class);
        projectAdapter.setPoints(model.projects);
        model.getProjects().observe(getViewLifecycleOwner(),
                new androidx.lifecycle.Observer<List<Project>>() {
                    @Override
                    public void onChanged(List<Project> projects) {
                        projectAdapter.setPoints(projects);
                    }
                });

        buttonAdd = binding.buttonAdd;
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProject(v);
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void addProject (View view){
        Navigation.findNavController(view).navigate(R.id.action_ProjectListFragment_to_projectFormFragment);
    }
}