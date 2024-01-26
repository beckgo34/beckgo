package kr.co.icia.mapline.util;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.icia.mapline.util.KakaoApiUtil.KakaoDirections.Route.Section.Road;

public class KakaoApiUtil {
	private static final String REST_API_KEY = "f4ac21a8529620360cdcb4daa4637bc4";

	/**
	 * 자동차 길찾기
	 * 
	 * @param from 출발지
	 * @param to   도착지
	 * @return 이동 좌표 목록
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static List<Point> getVehiclePaths(Point from, Point to) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String url = "https://apis-navi.kakaomobility.com/v1/directions";
		url += "?origin=" + from.getX() + "," + from.getY();
		url += "&destination=" + to.getX() + "," + to.getY();
		HttpRequest request = HttpRequest.newBuilder()//
				.header("Authorization", "KakaoAK " + REST_API_KEY)//
				.header("Content-Type", "application/json")//
				.uri(URI.create(url))//
				.GET()//
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String responseBody = response.body();

		System.out.println("자동차길찾기 응답 본문: " + responseBody);
		
		List<Point> pointList = new ArrayList<Point>();

		KakaoDirections kakaoDirections = new ObjectMapper().readValue(responseBody, KakaoDirections.class);
		List<Road> roads = kakaoDirections.getRoutes().get(0).getSections().get(0).getRoads();
		for (Road road : roads) {
			List<Double> vertexes = road.getVertexes();
			for (int i = 0; i < vertexes.size(); i++) {
				pointList.add(new Point(vertexes.get(i), vertexes.get(++i)));
			}
		}

		return pointList;

	}

	/**
	 * 주소 -> 좌표 변환
	 * 
	 * @param address 주소
	 * @return 좌표
	 */
	public static Point getPointByAddress(String address) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		String url = "https://dapi.kakao.com/v2/local/search/address.json";
		url += "?query=" + URLEncoder.encode(address, "UTF-8");
		HttpRequest request = HttpRequest.newBuilder()//
				.header("Authorization", "KakaoAK " + REST_API_KEY)//
				.uri(URI.create(url))//
				.GET()//
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String responseBody = response.body();
		System.out.println(responseBody);

		KakaoAddress kakaoAddress = new ObjectMapper().readValue(responseBody, KakaoAddress.class);
		List<KakaoAddress.Document> documents = kakaoAddress.getDocuments();
		if (documents.isEmpty()) {
			return null;
		}
		KakaoAddress.Document document = documents.get(0);
		return new Point(document.getX(), document.getY());
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class KakaoDirections {
		private List<Route> routes;

		public List<Route> getRoutes() {
			return routes;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Route {
			private List<Section> sections;

			public List<Section> getSections() {
				return sections;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			public static class Section {
				private List<Road> roads;

				public List<Road> getRoads() {
					return roads;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				public static class Road {
					private List<Double> vertexes;

					public List<Double> getVertexes() {
						return vertexes;
					}

				}

			}
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	private static class KakaoAddress {
		private List<Document> documents;

		public List<Document> getDocuments() {
			return documents;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Document {
			private Double x;
			private Double y;

			public Double getX() {
				return x;
			}

			public Double getY() {
				return y;
			}
		}
	}

	public static class Point {
		private Double x;
		private Double y;

		public Point(Double x, Double y) {
			this.x = x;
			this.y = y;
		}

		public Double getX() {
			return x;
		}

		public Double getY() {
			return y;
		}

	}
}
