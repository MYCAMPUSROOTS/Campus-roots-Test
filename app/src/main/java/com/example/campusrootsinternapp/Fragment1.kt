import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campusrootsinternapp.FragmentCourse
import com.example.campusrootsinternapp.R
import com.example.campusrootsinternapp.adapter.Adapter
import com.example.campusrootsinternapp.base.BaseFragment
import com.example.campusrootsinternapp.databinding.FragmentDashboardBinding
import com.example.campusrootsinternapp.model.CourseItem
import com.example.campusrootsinternapp.util.observeChange

class Fragment1 : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var courseList: ArrayList<CourseItem>

    private lateinit var courseContainer: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the course list
        courseList = ArrayList()
        courseList.add(CourseItem("MTH 101", "Student Elementary Mathematics TA", "Markus Callaway", 7, true))
        courseList.add(CourseItem("PHY 211", "Principles of Quantum Physics", "Markus Callaway", 7, true))
        courseList.add(CourseItem("BUS 103", "Introduction to Business", "Markus Callaway", 7, false))
        courseList.add(CourseItem("PHY 211", "Principles of Quantum Physics", "Markus Callaway", 7, false))
        courseList.add(CourseItem("MTH 101", "Student Elementary Mathematics TA", "Markus Callaway", 7, false))
        courseList.add(CourseItem("BUS 103", "Introduction to Business", "Markus Callaway", 7, false))

                // Set up the adapter
        adapter = Adapter(courseList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        adapter.selectedCourse.observeChange(viewLifecycleOwner) { selectedCourse ->
            val fragment = FragmentCourse()
            fragment.arguments = Bundle().apply {
                putString("courseName", selectedCourse.courseName)
                putString("courseTitle", selectedCourse.courseTitle)
                putString("courseLecturer", selectedCourse.courseLecturer)
                putInt("courseChannels", selectedCourse.courseChannels)
            }
            mFragmentNavigation.pushFragment(fragment)

        }
    }
}
