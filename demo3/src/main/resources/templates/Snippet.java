package templates;

public class Snippet {
	<select id="findById" parameterType="Long" resultType="com.example.student.dto.StudentDTO">
			select * from student_table where id=#{id}
		</select>
}

