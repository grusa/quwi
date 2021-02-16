package earth.sochi.quwi.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import earth.sochi.quwi.R;
import earth.sochi.quwi.data.Project;

public class ProjectAdapter extends
        RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
        String [] localDataSet;
        Context c;
        private static final String TAG = "PA";
        List<Project> mProjects;
        public LayoutInflater mLayoutInflater;

        public static class  ViewHolder extends  RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.name_Tv);
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(),"position " +getAdapterPosition()+" "
                            +textView.getText(),Toast.LENGTH_LONG ).show();
                    //TODO remove 
                    return false;
                }
            });
        }
        public TextView getTextView() {
            return textView;
        }
        }
        public ProjectAdapter (Context context) {
         mLayoutInflater=LayoutInflater.from(context);

         this.c = context;
     }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int  viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_project, viewGroup, false);
        return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position) {
            if (mProjects.size()>=0) {
                Project current = mProjects.get(position);
                viewHolder.textView.setText(current.name);
            }
        }
        @Override
        public int getItemCount() {
            if (mProjects!=null) {
                return mProjects.size();
            } else return 0;
        }

        void setPoints(List<Project> projects) {
         //if (mPoint!=null) mPoint.clear();
         mProjects = projects;
         notifyDataSetChanged();
     }

}
