package com.oss.kakaopay.barofn.dao;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

class BeanSample {
	 
    private String first;
    private String second;
    private String third;
    private String fourth;
    // getters and setters
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThird() {
		return third;
	}
	public void setThird(String third) {
		this.third = third;
	}
	public String getFourth() {
		return fourth;
	}
	public void setFourth(String fourth) {
		this.fourth = fourth;
	}
	@Override
	public String toString() {
		return "BeanSample [first=" + first + ", second=" + second + ", third=" + third + ", fourth=" + fourth + "]";
	}
    
    
}


public class csvReaderTest01 {
	
	static List<BeanSample> beans;
	static BeanSample bean;
	static int index;
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			System.out.println(readAllExample());
			List<BeanSample> resultlst = readAllExample();
			for (BeanSample beanSample : resultlst) {
				System.out.println(beanSample.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	public String oneByOneExample() throws Exception {
	    Reader reader = Files.newBufferedReader(Paths.get(
	      ClassLoader.getSystemResource("csv/twoColumn.csv").toURI()));
	    return oneByOne(reader).toString();
		/* return CsvReaderExamples.oneByOne(reader).toString(); */
	}
	
	
	public static List<BeanSample> readAll(Reader reader) throws Exception {
		//CSVReader csvReader = new CSVReader(reader);
		CSVParser parser = new CSVParserBuilder()
			    .withSeparator(',')
			    .withIgnoreQuotations(true)
			    .build();
		 
		CSVReader csvReader = new CSVReaderBuilder(reader)
		    .withSkipLines(1)
		    .withCSVParser(parser)	
		    .build();
		
		List<String[]> list = new ArrayList<>();
		list = csvReader.readAll();
		reader.close();
		csvReader.close();
		
		beans = new ArrayList<BeanSample>();
		index = 0;
		for (String[] strings : list) {
		bean = new BeanSample();
//			System.out.println(strings);
			System.out.print(
					strings[0] + ", " +
					strings[1] + ", " +
					strings[2] + ", " +
					strings[3] + ", " + 
					strings[4] + ", " +
					strings[5]);
			bean.setFirst(strings[0]);
			bean.setSecond(strings[1]);
			bean.setThird(strings[4]);
			bean.setFourth(strings[5]);
			beans.add(index, bean);
			System.out.println(index);
			System.out.println(beans.toString());
			index++;
		}
		//System.out.println(list.size());
		return beans;
//		return list;
	}

//	public static String readAllExample() throws Exception {
	public static List<BeanSample> readAllExample() throws Exception {
		Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("static/데이터_거래내역.csv").toURI()));
		return readAll(reader);
//		return readAll(reader).toString();
		/* return CsvReaderExamples.readAll(reader).toString(); */
	}

	public List<String[]> oneByOne(Reader reader) throws Exception {
		List<String[]> list = new ArrayList<>();
		//CSVReader csvReader = new CSVReader(reader);

		CSVParser parser = new CSVParserBuilder()
			    .withSeparator(',')
			    .withIgnoreQuotations(true)
			    .build();
		 
		CSVReader csvReader = new CSVReaderBuilder(reader)
		    .withSkipLines(1)
		    .withCSVParser(parser)	
		    .build();
		
		
		String[] line;
		while ((line = csvReader.readNext()) != null) {
			list.add(line);
		}
		reader.close();
		csvReader.close();
		System.out.println(list.size());
		return list;
	}
}
