package example.android;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.TextView;
import android.widget.Toast;

public class MyOnDragListener implements OnDragListener {

	private TextView textView;
	
	public MyOnDragListener(TextView view) {
		textView = view;
		textView.getBackground();
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		// Defines a variable to store the action type for the incoming event
		final int action = event.getAction();

		CharSequence dragData;
		// Handles each of the expected events
		switch (action) {

		case DragEvent.ACTION_DRAG_STARTED:

			// Determines if this View can accept the dragged data
			if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
				
				textView.setBackgroundColor(Color.BLUE);

				// returns true to indicate that the View can accept the dragged
				// data.
				return true;

			} else {
				// Returns false. During the current drag and drop operation,
				// this View will
				// not receive events again until ACTION_DRAG_ENDED is sent.
				return false;
			}

		case DragEvent.ACTION_DRAG_ENTERED:
			
			textView.setBackgroundColor(Color.GREEN);
			


			return true;

		case DragEvent.ACTION_DRAG_LOCATION:

			return true;

		case DragEvent.ACTION_DRAG_EXITED:

			textView.setBackgroundColor(Color.parseColor("#999999"));

			return true;

		case DragEvent.ACTION_DROP:

			// Gets the item containing the dragged data
			ClipData.Item item = event.getClipData().getItemAt(0);

			// Gets the text data from the item.
			dragData = item.getText();

			// Displays a message containing the dragged data.
			textView.setText(dragData);
			
			textView.setBackgroundColor(Color.parseColor("#999999"));

			// Returns true. DragEvent.getResult() will return true.
			
			int[] x = new int[0];
			int y = x[1];
			return true;

		case DragEvent.ACTION_DRAG_ENDED:

			textView.setBackgroundColor(Color.parseColor("#999999"));

			// Does a getResult(), and displays what happened.
			if (event.getResult()) {
				Toast.makeText(textView.getContext(), "The drop was handled.", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(textView.getContext(), "The drop didn't work.", Toast.LENGTH_LONG).show();
			}

			// returns true; the value is ignored.
			return true;

			// An unknown action type was received.
		default:
			Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
			return false;
		}

	}
}
