package path;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbutil.DBUtil;
import gmap.DistanceCalculator;
import object.LatAndLng;
import object.Location;
import selectLocation.MovieDAO;

@WebServlet("/selectpath")
public class PathServlet extends HttpServlet {
	SelectPathDAO selectPathDao = new SelectPathDAO();
	MovieDAO movieDao = new MovieDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;

		int[] selectedNos = new int[4];

		String selectedLocationNos = req.getParameter("selectedLocationNos");

		System.out.println("getParameter로 받아오 지역:" + selectedLocationNos);

		if (selectedLocationNos != null && !selectedLocationNos.isEmpty()) {
			String[] numbersString = selectedLocationNos.split(",");
//			selectedNos = new int[numbersString.length];
			System.out.println("numbersString" + numbersString);

			for (int i = 0; i < numbersString.length; i++) {
//				selectedNos[i] = Integer.parseInt(numbersString[i].trim());

				String number = numbersString[i].replaceAll("[\\[\\]]", "").trim();
				selectedNos[i] = Integer.parseInt(number);

			}

		}

		System.out.println(selectedNos[0]);
		System.out.println(selectedNos[1]);
		System.out.println(selectedNos[2]);
		System.out.println(selectedNos[3]);

		// 선택된 영화 번호

		int[] selectedMovies = new int[5];

		String[] movieNumber = req.getParameterValues("movieNumber");

//   System.out.println(movieNumber.length);

		for (int i = 0; i < movieNumber.length; i++) {
//       System.out.println(movieNumber[i]);
		}

		for (int i = 0; i < movieNumber.length; i++) {
			selectedMovies[i] = Integer.parseInt(movieNumber[i].trim());

//       System.out.println(selectedMovies[i]);
		}

		System.out.println("0번 째 영화넘버: " + selectedMovies[0]);
		System.out.println("1번 째 영화넘버: " + selectedMovies[1]);
		System.out.println("2번 째 영화넘버: " + selectedMovies[2]);
		System.out.println("3번 째 영화넘버: " + selectedMovies[3]);
		System.out.println("4번 째 영화넘버: " + selectedMovies[4]);

		try {
			conn = DBUtil.getConnection();

			List<Location> selectedLocation = selectPathDao.getLocationList(conn, selectedNos);
			List<Location> entireSelectedList = new ArrayList<Location>();

			for (int i = 0; i < selectedMovies.length; i++) {

				List<Location> entireSelectedListPerMovie = movieDao.selectLocationList(selectedMovies[i]);
				entireSelectedList.addAll(entireSelectedListPerMovie);

			}

			List<Location> firstLocationList = DistanceCalculator.getFirstLocation(selectedLocation);

			List<Location> secondLocationList = DistanceCalculator.getSecondLocation(firstLocationList,
					entireSelectedList);

			List<Location> thirdLocationList = DistanceCalculator.getThirdLocation(firstLocationList,
					secondLocationList, entireSelectedList);

			System.out.println("firstLocationList의 크기" + firstLocationList.size());
			for (int i = 0; i < firstLocationList.size(); i++) {

				System.out.println("firstLocationList의 위도: " + firstLocationList.get(i).getLatitude());
				System.out.println("firstLocationList의 경도: " + firstLocationList.get(i).getLongitude());
			}
			System.out.println("secondLocationList의 크기" + firstLocationList.size());
			for (int i = 0; i < secondLocationList.size(); i++) {

				System.out.println("secondLocationList의 위도: " + secondLocationList.get(i).getLatitude());
				System.out.println("secondLocationList의 경도: " + secondLocationList.get(i).getLongitude());
			}
			System.out.println("thirdLocationList의 크기" + firstLocationList.size());
			for (int i = 0; i < thirdLocationList.size(); i++) {

				System.out.println("thirdLocationList의 위도: " + thirdLocationList.get(i).getLatitude());
				System.out.println("thirdLocationList의 경도: " + thirdLocationList.get(i).getLongitude());
			}

			LatAndLng[] firstlatAndLngs = new LatAndLng[4];
			for (int i = 0; i < firstLocationList.size(); i++) {
				firstlatAndLngs[i] = new LatAndLng(firstLocationList.get(i).getLatitude(),
						firstLocationList.get(i).getLongitude());

			}
			LatAndLng[] secondlatAndLngs = new LatAndLng[4];
			for (int i = 0; i < secondLocationList.size(); i++) {
				secondlatAndLngs[i] = new LatAndLng(secondLocationList.get(i).getLatitude(),
						secondLocationList.get(i).getLongitude());

			}
			LatAndLng[] thirdlatAndLngs = new LatAndLng[4];
			for (int i = 0; i < thirdLocationList.size(); i++) {
				thirdlatAndLngs[i] = new LatAndLng(thirdLocationList.get(i).getLatitude(),
						thirdLocationList.get(i).getLongitude());

			}

			req.setAttribute("firstlatAndLngs", firstlatAndLngs);
			req.setAttribute("secondlatAndLngs", secondlatAndLngs);
			req.setAttribute("thirdlatAndLngs", thirdlatAndLngs);

			req.getRequestDispatcher("/WEB-INF/selectpathpage/pathmap.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}

	}

}