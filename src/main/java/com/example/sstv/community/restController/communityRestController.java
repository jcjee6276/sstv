package com.example.sstv.community.restController;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.example.sstv.common.Data;
import com.example.sstv.community.Comments;
import com.example.sstv.community.Community;
import com.example.sstv.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/community/*")
public class communityRestController {
    private CommunityService communityService;

    @Autowired
    public communityRestController(CommunityService communityService) {
        this.communityService = communityService;
    }


    @GetMapping(value = "getWriting/{writingNo}")
    public Data getWriting(@PathVariable int writingNo) {


        Data data = new Data("success", communityService.getWriting(writingNo));

        return data;
    }

    @PostMapping(value = "addWriting", consumes = "application/json;")
    public Data addWriting(@RequestBody Community community) {
        System.out.println("test community : " + community);

        communityService.addWriting(community);
        Data data = new Data("success", "성공");
        return data;
    }

    @GetMapping(value = "deleteWriting/{writingNo}")
    public Data deleteWriting(@PathVariable int writingNo) {
        communityService.deleteWriting(writingNo);
        Data data = new Data("success", writingNo + "삭제 성공");
        return data;
    }

    @GetMapping(value = "updateWriting/{writingNo}")
    public Data updateWriting(@PathVariable int writingNo) {

        Data data = new Data("success", communityService.getWriting(writingNo));

        return data;
    }

    @GetMapping(value="addView/{writingNo}")
    public Data addView(@PathVariable int writingNo){
        communityService.addView(writingNo);
        Data data = new Data("success","");
        return data;
    }



    @GetMapping(value="addNotice/{writingNo}")
    public Data addNotice(@PathVariable int writingNo){
        communityService.addNotice(writingNo);
        Data data = new Data("success","");
        return data;
    }

    @PostMapping(value = "updateWriting", consumes = "application/json;")
    public Data updateWriting(@RequestBody Community community) {
        System.out.println(community);
        communityService.updateWriting(community);
        Data data = new Data("success", "업데이트 성공");
        return data;

    }

    @PostMapping(value = "addComments", consumes = "application/json;")
    public Data addComments(@RequestBody Comments comments) {
        System.out.println("test : " + comments);

        communityService.addComments(comments);

        Data data = new Data("success", "댓글 추가 성공");
        return data;
    }

    @GetMapping(value = "getCommentsList/{writingNo}")
    public Data getCommentsList(@PathVariable int writingNo) {
        Map<String, Object> map = communityService.getCommentsList(writingNo);
        Data data = new Data("success", map.get("list"));
        return data;
    }

    @GetMapping(value = "deleteComments/{commentsNo}")
    public Data deleteComments(@PathVariable int commentsNo) {
        communityService.deleteComments(commentsNo);
        Data data = new Data("success", "댓글 삭제 성공");
        return data;
    }

    @GetMapping(value="getNotice/{hostUserId}")
    public Data getNotice(@PathVariable String hostUserId){



        Data data = new Data("success", communityService.getNotice(hostUserId));
        return data;
    }

    @GetMapping(value = "writingList/{hostUserId}") // User 받아서 writinglist 걸러내기 @pathvariable userid 추가
    public Data getWritingList(@PathVariable String hostUserId) {
        Map<String, Object> map = communityService.getWritingList(hostUserId);
        map.put("count", communityService.getWritingCount(hostUserId));
        System.out.println(map.get("list"));
        Data data = new Data("success", map.get("list"), map.get("count"));
        return data;
    }

    @GetMapping(value = "searchWriting/{searchKeyword}")
    public Data getSearchWriting(@PathVariable String searchKeyword) {

        Map<String, Object> map = communityService.getSearchWriting(searchKeyword);


        Data data = new Data("success", map.get("list"));
        System.out.println(map.get("list"));
        return data;
    }

    @GetMapping(value = "getS3BucketList")
    public Data getS3BucketList() {
        final String endPoint = "https://kr.object.ncloudstorage.com";
        final String regionName = "kr-standard";
        final String accessKey = "z4Xcnb9Fi7MmuSeksVf4";
        final String secretKey = "nt9eOEVgBxjdmjqOgP9Xee44ADNmEDT171bekE2u";

        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        try {
            List<Bucket> buckets = s3.listBuckets();
            System.out.println("Bucket List: ");
            for (Bucket bucket : buckets) {
                System.out.println("    name=" + bucket.getName() + ", creation_date=" + bucket.getCreationDate() + ", owner=" + bucket.getOwner().getId());
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        Data data = new Data("success", "");
        return data;
    }

    @GetMapping(value = "videoDownload")
    public Data videoDownload() throws FileNotFoundException {
        final String endPoint = "https://kr.object.ncloudstorage.com";
        final String regionName = "kr-standard";
        final String accessKey = "z4Xcnb9Fi7MmuSeksVf4";
        final String secretKey = "nt9eOEVgBxjdmjqOgP9Xee44ADNmEDT171bekE2u";

// S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "advertise";
        String objectName = "cc27bc8b-29dd-47aa-b614-f5ecbedf6eb4.mp4";
        String downloadFilePath = "/Users/jeonjichang/sstv/src/main/resources/media/"+objectName;

// download object
        try {
            S3Object s3Object = s3.getObject(bucketName, objectName);
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFilePath));
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = s3ObjectInputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }

            outputStream.close();
            s3ObjectInputStream.close();
            System.out.format("Object %s has been downloaded.\n", objectName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Data data = new Data("success", "");
        return data;
    }

    @GetMapping(value="testupload")
    public Data testupload(@PathVariable String recordUrl) {

        final String endPoint = "https://kr.object.ncloudstorage.com";
        final String regionName = "kr-standard";
        final String accessKey = "z4Xcnb9Fi7MmuSeksVf4";
        final String secretKey = "nt9eOEVgBxjdmjqOgP9Xee44ADNmEDT171bekE2u";

// S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "hls";
// create folder
        String folderName = "livestation/thumbnails/";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);

        try {
            s3.putObject(putObjectRequest);
            System.out.format("Folder %s has been created.\n", folderName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }

// upload local file
        String objectName = folderName+"176032-739207-202306082029.jpg";
        String filePath = "/Users/jeonjichang/sstv/src/main/resources/media/176032-739207-202306082029.jpg";

        try {
            s3.putObject(bucketName, objectName, new File(filePath));
            s3.setObjectAcl(bucketName, objectName, CannedAccessControlList.PublicRead);
            System.out.format("Object %s has been created.\n", objectName);
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch(SdkClientException e) {
            e.printStackTrace();
        }

        Data data = new Data("success", "");
        return data;
    }


    @GetMapping(value="createThumbnails")
    public Data createThumbnails() {
        final String endPoint = "https://kr.object.ncloudstorage.com";
        final String regionName = "kr-standard";
        final String accessKey = "z4Xcnb9Fi7MmuSeksVf4";
        final String secretKey = "nt9eOEVgBxjdmjqOgP9Xee44ADNmEDT171bekE2u";

        String ffmpegPath = "/Users/jeonjichang/Downloads/ffmpeg";
        String fileName = "176032-739207-202306082029.jpg";
        String inputFilePath = "https://kr.object.ncloudstorage.com/hls/livestation/176032-739207-202306082029.mp4";
        String outputFilePath = "/Users/jeonjichang/sstv/src/main/resources/media/"+fileName;
        int timeInSeconds = 10;

        String bucketName = "https://kr.object.ncloudstorage.com/hls/livestation/thumbnails";

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();



        try {
            ProcessBuilder processBuilder = new ProcessBuilder(ffmpegPath, "-ss", String.valueOf(timeInSeconds), "-i", inputFilePath, "-vframes", "1", "-q:v", "2", outputFilePath);
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("썸네일 생성이 완료되었습니다.");
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

        Data data = new Data("success", "");
        return data;
    }

}
