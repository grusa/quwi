package earth.sochi.quwi.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import earth.sochi.quwi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String[] genDataSet;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    public ProjectListFragment() {
 // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectListFragment newInstance(String param1, String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        workoutsTypeViewModel=
//                ViewModelProvider(this).get(WorkoutsTypeViewModel::class.java)
        genDataSet=new String[1];
        genDataSet[0]="No any projects";
        ProjectAdapter projectAdapter = new ProjectAdapter(genDataSet);
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        RecyclerView recyclerView = //container.findViewById(R.id.projectsRv);
        (RecyclerView) view.findViewById(R.id.projectsRv);

        recyclerView.setAdapter(projectAdapter);

        return view;
    }
}