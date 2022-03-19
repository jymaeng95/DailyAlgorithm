//package com.algorithm.Programmers.SK;
//
//import java.util.LinkedList;
//import java.util.PriorityQueue;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class Solution_2nd_2 {
//    public static void main(String[] args) {
//        String[] arr = {"1", "2", "4", "3", "3", "4", "1", "5"};
//        String[] process = {"read 1 3 1 2", "read 2 6 4 7", "write 4 3 3 5 2", "read 5 2 2 5", "write 6 1 3 3 9", "read 9 1 0 7"};
//
//        String[] arr = {"1","1","1","1","1","1","1"};
//        String[] process = {"write 1 12 1 5 8","read 2 3 0 2","read 5 5 1 2","read 7 5 2 5","write 13 4 0 1 3","write 19 3 3 5 5","read 30 4 0 6","read 32 3 1 5"};
//        String[] rst = solution(arr, process);
//        for (String s : rst) System.out.println("s = " + s);
//    }
//
//    static class Process implements Comparable<Process> {
//        private int type;
//        private int start;
//        private int elapsed;
//        private int startIdx;
//        private int endIdx;
//        private String changeData;
//
//        public Process(int type, int start, int elapsed, int startIdx, int endIdx, String changeData) {
//            this.type = type;
//            this.start = start;
//            this.elapsed = elapsed;
//            this.startIdx = startIdx;
//            this.endIdx = endIdx;
//            this.changeData = changeData;
//        }
//
//        public Process(int type, int start, int elapsed, int startIdx, int endIdx) {
//            this.type = type;
//            this.start = start;
//            this.elapsed = elapsed;
//            this.startIdx = startIdx;
//            this.endIdx = endIdx;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public int getStart() {
//            return start;
//        }
//
//        public int getElapsed() {
//            return elapsed;
//        }
//
//        public int getStartIdx() {
//            return startIdx;
//        }
//
//        public int getEndIdx() {
//            return endIdx;
//        }
//
//        public String getChangeData() {
//            return changeData;
//        }
//
//        @Override
//        public int compareTo(Process o) {
//            if(this.type == o.type) return Integer.compare(this.start+this.elapsed, o.start+o.elapsed);
//
//            return Integer.compare(this.type, o.type);
//        }
//    }
//
//    private static Queue<Process> queue;
//
//    private static String[] solution(String[] arr, String[] process) {
//        queue = new LinkedList<>();
//
//        // 초기화
//        int readCount = 0;
//        for (String prc : process) {
//            StringTokenizer st = new StringTokenizer(prc);
//            String type = st.nextToken();
//            int start = Integer.parseInt(st.nextToken());
//            int elapsed = Integer.parseInt(st.nextToken());
//            int startIdx = Integer.parseInt(st.nextToken());
//            int endIdx = Integer.parseInt(st.nextToken());
//            if (type.equals("write")) {
//                String changeData = st.nextToken();
//                queue.add(new Process(0, start, elapsed, startIdx, endIdx, changeData));
//            } else {
//                readCount++;
//                queue.add(new Process(1, start, elapsed, startIdx, endIdx));
//            }
//        }
//
//        return arrayProcessing(readCount, arr);
//    }
//
//    private static String[] arrayProcessing(int readCount, String[] arr) {
//        String[] rst = new String[readCount+1];
//        PriorityQueue<Process> waitingQueue = new PriorityQueue<>();
//        Process startProcess = queue.peek();
//        int second = startProcess.getStart();
////        waitingQueue.add(startProcess);
//        int type = startProcess.getType();
//
//        /**
//         * 한 번에 여러 프로세스가 배열에서 동시에 읽기 작업을 수행할 수 있습니다.
//         * 배열에 읽기 작업을 수행 중인 경우, 새로운 읽기 요청 프로세스는 즉시 작업을 수행할 수 있습니다.
//         * 한 번에 하나의 프로세스만 배열에서 쓰기 작업을 수행할 수 있습니다.
//         * 배열에 쓰기 작업을 수행 중인 경우, 새로운 읽기, 쓰기 요청 프로세스는 모두 대기해야 합니다.
//         * 배열에 읽기 작업을 수행 중인 경우, 새로운 쓰기 요청 프로세스는 모두 대기해야 합니다.
//         * 하나 이상의 쓰기 작업이 대기 중인 경우, 새로운 읽기 요청 또한 대기해야 합니다.
//         * 대기 중인 읽기, 쓰기 작업 중에서 다음으로 작업할 프로세스를 선택할 때
//         * 읽기 작업보다 쓰기 작업을 먼저 수행합니다.
//         * 쓰기 작업이 여러 개라면, 먼저 요청된 쓰기 작업을 먼저 수행합니다.
//         * 대기 중인 작업을 배열에서 수행하려 함과 동시에 새로운 작업 요청이 들어온다면, 새 작업 요청을 포함하여 다음으로 작업할 프로세스를 선택합니다.
//         * 예를 들어, 10초에 쓰기 작업이 끝났고, 읽기 작업만 대기 중인 경우, 10초에 새로운 쓰기 작업 요청이 들어왔다면, 쓰기 작업을 먼저 처리합니다.
//         * read 1 3 1 2,
//         * read 2 6 4 7,
//         * write 4 3 3 5 2,
//         * read 5 2 2 5,
//         * write 6 1 3 3 9,
//         * read 9 1 0 7
//         */
//        int count = 0;
//        int empty = 1;
//        while(!queue.isEmpty() || !waitingQueue.isEmpty()) {
//            // 대기 중인 작업 배열 동시 새로운 작업 요청 들어오는 경우 새 작업 대기열에 넣고 비교 후 빼기
//            if(!queue.isEmpty() && queue.peek().getStart() <= second) {
//                if(type == 1 && waitingQueue.isEmpty()) waitingQueue.add(queue.poll());   //이전 타입이 읽기인 경우 한개만 넣어줌
//                else if(type == 0 && waitingQueue.isEmpty()){
//                    while(!queue.isEmpty() && queue.peek().getStart() <= second) waitingQueue.add(queue.poll());
//                }
//            }
//            else if(waitingQueue.isEmpty() && !queue.isEmpty() && queue.peek().getStart() > second){
//                second++;
//                empty++;
//                continue;
//            }
//
//            Process curProcess = waitingQueue.poll();
//
//            // 현재 프로세스가 읽기 작업인 경우
//            if(curProcess.getType() == 1) {
//                StringBuilder read = new StringBuilder();
//                // 구간 읽기
//                for(int index = curProcess.getStartIdx(); index <= curProcess.getEndIdx(); index++) {
//                    read.append(arr[index]);
//                }
//
//                // 처리 시간 더해주기
//                if(type == 1 && curProcess.getStart() + curProcess.getElapsed() > second) second += curProcess.getElapsed() - (second - curProcess.getStart());
//                else if(type == 0) second += curProcess.getElapsed();
//                rst[count] = read.toString();
//                count++;
//                type = curProcess.getType();
//            }
//            // 현재 프로세스가 쓰기 작업인 경우
//            else {
//                for(int index = curProcess.getStartIdx(); index <= curProcess.getEndIdx(); index++) {
//                    arr[index] = curProcess.getChangeData();
//                }
//                second += curProcess.getElapsed();
//                type = curProcess.getType();
//            }
//        }
//        rst[readCount] = String.valueOf(second-empty);
//        return rst;
//    }
//}
