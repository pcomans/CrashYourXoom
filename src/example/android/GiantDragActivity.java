package example.android;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GiantDragActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		final ImageView iv = (ImageView) findViewById(R.id.bomb);
		iv.setOnLongClickListener(new OnLongClickListener() {

			public boolean onLongClick(View v) {
				ClipData clipData = ClipData.newPlainText("message", "boom!");

				// Instantiates the drag shadow builder.
				DragShadowBuilder myShadow = new DragShadowBuilder(iv);

				// Starts the drag

				v.startDrag(clipData, // the data to be dragged
						myShadow, // the drag shadow builder
						null, // no need to use local data
						0 // flags (not currently used, set to 0)
				);
				
				return true;
			}
		});
		
		TextView left  = (TextView) findViewById(R.id.left);
		left.setOnDragListener(new MyOnDragListener(left));
		
		TextView right = (TextView) findViewById(R.id.right);
		right.setOnDragListener(new MyOnDragListener(right));
	}
}