package earth.sochi.quwi.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import earth.sochi.quwi.R;

 public class ProjectAdapter extends
        RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
        String [] localDataSet;
        public ProjectAdapter(String[] dataSet) {
             localDataSet = dataSet;
         }
        public static class  ViewHolder extends  RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.name_Tv);
        }
        public TextView getTextView() {
            return textView;
        }
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int  viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_project, viewGroup, false);
        return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position) {
            viewHolder.textView.setText(localDataSet[position]);
        }
        @Override
        public int getItemCount() {
            return localDataSet.length;}
}
