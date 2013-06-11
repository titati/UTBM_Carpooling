package fr.utbm.carpooling.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.CommentAdapter;
import fr.utbm.carpooling.model.Comment;

import java.util.ArrayList;


public class ProfileStatsFragment extends Fragment {

	public ProfileStatsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_profile_stats, container,
				false);
	}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        ListView commentList = (ListView) getView().findViewById(
                R.id.profile_stats_listview_comments);

        ArrayList<Comment> comments = new ArrayList<Comment>();
        Comment c1 = new Comment();
        c1.setName("B. Laurans");
        c1.setComment("Salut, ceci est un test de commentaire");

        Comment c2 = new Comment();
        c2.setName("M. Grandidier");
        c2.setComment("Et ta soeur c'est un test de commentaire ?");

        Comment c3 = new Comment();
        c3.setName("J. Caesar");
        c3.setComment("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");

        comments.add(c1);
        comments.add(c2);
        comments.add(c3);
        comments.add(c2);
        comments.add(c1);
        comments.add(c2);


        CommentAdapter adapter = new CommentAdapter(getActivity(), R.layout.view_comment_item, comments);

        commentList.setAdapter(adapter);

        super.onActivityCreated(savedInstanceState);
    }
}
