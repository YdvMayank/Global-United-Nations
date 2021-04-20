package dev.rao.globalunitednations.mun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.rao.globalunitednations.R;
import dev.rao.globalunitednations.model.Mun_Rules;

public class MunAdapter extends RecyclerView.Adapter<MunAdapter.MovieVH> {

    private static final String TAG = "MovieAdapter";
    List<Mun_Rules> rulesList;

    public MunAdapter(List<Mun_Rules> rules) {
        this.rulesList = rules;
    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mun_expendable, parent, false);
        return new MovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVH holder, int position) {

        Mun_Rules rules = rulesList.get(position);
        holder.titleTextView.setText(rules.getTitle());
        holder.ratingTextView.setText(rules.getDescription());

        boolean isExpanded = rulesList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return rulesList.size();
    }

    class MovieVH extends RecyclerView.ViewHolder {

        private static final String TAG = "MovieVH";

        RelativeLayout expandableLayout;
        TextView titleTextView, yearTextView, ratingTextView, plotTextView;

        public MovieVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.tv_Title);
            ratingTextView = itemView.findViewById(R.id.tv_description);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Mun_Rules mun_rules = rulesList.get(getAdapterPosition());
                    mun_rules.setExpanded(!mun_rules.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}

