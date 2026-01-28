package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Long cnt = 0L;
    static final Map<Long, FamousSaying> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String str = br.readLine().trim();

            if (str.equals("종료")) {
                break;
            } else if (str.equals("등록")) {
                System.out.print("명언 : ");
                String content = br.readLine();
                System.out.print("작가 : ");
                String author = br.readLine();
                FamousSaying famousSaying = new FamousSaying(++cnt, content, author);
                map.put(famousSaying.getId(), famousSaying);
                System.out.println(cnt + "번 명언이 등록되었습니다.");
            } else if (str.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                List<Long> keys = new ArrayList<>(map.keySet());
                Collections.sort();
                Collections.reverse(keys);
                for (Long id : keys) {
                    FamousSaying fs = map.get(id);
                    System.out.printf("%d / %s / %s\n", fs.getId(), fs.getAuthor(), fs.getContent());
                }
            }
            else if(str.startsWith("삭제?id=")){
                String idStr=str.split("id=")[1];
                Long id = Long.parseLong(idStr);
                if(map.containsKey(id)){
                    map.remove(id);
                    System.out.println(id+"번 명언이 삭제되었습니다.");
                }
                else
                    System.out.println(id+"번 명언은 존재하지 않습니다.");
            }
            else if(str.startsWith("수정?id=")){
                String idStr=str.split("id=")[1];
                Long id = Long.parseLong(idStr);
                if(map.containsKey(id)){
                    FamousSaying nowFS = map.get(id);
                    System.out.println("명언(기존) : "+nowFS.getContent());
                    System.out.print("명언 : ");
                    String newContent = br.readLine();
                    System.out.println("작가(기존) : "+ nowFS.getAuthor());
                    System.out.print("작가 : ");
                    String  newAuthor= br.readLine();
                    FamousSaying newFS  = new FamousSaying(id,newContent,newAuthor);
                    map.put(id,newFS);
                    System.out.println(id +"번 명언이 수정되었습니다.");
                }
                else
                    System.out.println(id+"번 명언은 존재하지않습니다.");
            }
        }
    }
}
