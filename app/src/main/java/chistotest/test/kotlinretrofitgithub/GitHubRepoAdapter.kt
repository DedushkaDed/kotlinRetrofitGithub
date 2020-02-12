package chistotest.test.kotlinretrofitgithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class GitHubRepoAdapter(context: Context, private val values: List<GitHubRepo>) : ArrayAdapter<GitHubRepo>(context, R.layout.list_item_pagination, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView

        if (row == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            row = inflater.inflate(R.layout.list_item_pagination, parent, false)
        }

        val textView = row!!.findViewById<View>(R.id.list_item_pagination_text) as TextView

        val item = values[position]
        val message = item.name
        textView.text = message

        return row
    }
}