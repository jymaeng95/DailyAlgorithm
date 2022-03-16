package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Solution_17686_파일명_정렬 {
    public static void main(String[] args) {
        String[] files = {"F-5","F-4"};

        String[] rst = solution(files);
    }

    static class File implements Comparable<File>{
        private String head;
        private int number;
        private int sequence;
        private String originalName;

        public File(String head, int number, int sequence, String originalName) {
            this.head = head;
            this.number = number;
            this.sequence = sequence;
            this.originalName = originalName;
        }

        public String getOriginalName() {
            return originalName;
        }

        @Override
        public int compareTo(File o) {
            if(this.head.equals(o.head)) {
                if(this.number == o.number)
                    return Integer.compare(this.sequence, o.sequence);
                return Integer.compare(this.number, o.number);
            }
            return this.head.compareTo(o.head);
        }
    }
    private static String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for(int sequence = 0 ; sequence < files.length; sequence++) {
            StringBuilder sb = new StringBuilder();
            String head = "";
            String number = "";
            for(char c : files[sequence].toCharArray()) {
                if(Character.isDigit(c) && head.length() < 1) {
                    head = sb.toString();
                    sb = new StringBuilder();
                }
                else if(!Character.isDigit(c) && head.length() > 0) {

                    break;
                }
                sb.append(c);
            }
            number = sb.toString();
            fileList.add(new File(head.toLowerCase(Locale.ROOT), Integer.parseInt(number), sequence, files[sequence]));
        }

        Collections.sort(fileList);
        String[] fileSort = new String[files.length];
        for(int loop = 0; loop <fileSort.length; loop++) {
            fileSort[loop] = fileList.get(loop).getOriginalName();
        }
        return fileSort;
    }
}
