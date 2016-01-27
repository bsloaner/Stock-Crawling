import java.net.URL;

import java.util.Scanner;

public class stock {

	public static void main(String[] args) {

		// List of Stocks

		String[] listOfStock = { "AAPL", "GOOG", "BAC", "TVIX", "XIV", "KMI" };

							// Goes through the list of stocks and collects information

		for (int i = 0; i < listOfStock.length; i++) {

							// resets and holds the content of the web page

			String urlInfo = "";  //WHY This?

							// Selects the stock one at a time

			String stockAbr = listOfStock[i];

			double stockPrice = 0;

			double stockChange = 0;

							// creates a long string of the page content

			urlInfo = getURLInfo(stockAbr);

							// finds the change in stock
				
							// findStockChange(urlInfo);

							// set the stock price of the company

			stockPrice = findStockPrice(urlInfo);

							// sets the stock change of the company

			stockChange = findStockChange(urlInfo);

			System.out.println("stock name: " + listOfStock[i]);

			System.out.println("stock price: " + stockPrice);

			System.out.println("stock change: " + stockChange);

		}

	}

						// method used to get the content of the page

	public static String getURLInfo(String stock) {

							// holds the content of the page

		String longString = "";

						// used in collecting the page content one line at a time

		String s = "";

						// the search websites URL

		String searchPage = "http://www.marketwatch.com/investing/stock/"

		+ stock;

		try {

								// opens the web page and collects the page content

			URL url = new URL(searchPage);

			Scanner input = new Scanner(url.openStream());

			while (input.hasNext()) {

				s = input.nextLine();

				longString += s;

			}

		} catch (Exception ex) {

			System.out.println("Error: ");

		}

								// returns the page content

		return longString;

	}

								// method that finds the stock price

	public static double findStockPrice(String urlInfo) {

								// holds the position of the searched information

		int placeholder = 0;

							// used in finding the key word

		String infoArea = "";

		String numberExtract = "";

						// splits the web content by spaces into an array

		String[] finder = urlInfo.split(" ");

		// goes through the array and finds the string by key word that holds
		// the stock price information

		// key word: bgLast

		for (int i = 0; i < finder.length; i++) {

			if (finder[i].length() > 7) {

				infoArea = finder[i].substring(0, 6);

			}

			// remember where the key word is in the array

			// and breaks out of the loop

			if (infoArea.equals("bgLast")) {

				placeholder = i;

				break;

			}

		}

		// Used to stop out of bounds exceptions

		if (finder[placeholder].length() > 8) {

			numberExtract = finder[placeholder].substring(8);

		}

		int count = 0;

		String finalNumber = "";

		// Used to extract the number from the area and parses it to a double

		do {

			finalNumber += numberExtract.charAt(count);

			count++;

		} while (numberExtract.charAt(count) != '<');

		double number = Double.parseDouble(finalNumber);

		// returns the stock price

		return number;

	}

	public static double findStockChange(String urlInfo) {

		int placeholder = 0;

		String infoArea = "";

		String numberExtract = "";

		double stockChange = 0;

		boolean numberFinder = false;

		String[] finder = urlInfo.split(" ");

		for (int i = 0; i < finder.length; i++) {

			if (finder[i].length() > 15) {

				infoArea = finder[i].substring(6, 14);

			}

			if (finder[i].contains("bgChange")) {

				placeholder = i;

				break;

			}

		}

		infoArea = finder[placeholder];

		for (int j = 0; j < infoArea.length(); j++) {

			if (infoArea.charAt(j) == '>') {

				numberFinder = true;

			} else {

				if (infoArea.charAt(j) == '<') {

					break;

				}

				if (numberFinder == true) {

					numberExtract += infoArea.charAt(j);

				}

			}

		}

		stockChange = Double.parseDouble(numberExtract);

		return stockChange;

	}

	public static void findStockChangePercentage(String urlInfo) {

	}

}