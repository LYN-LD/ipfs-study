package cn.lyn.ipfs;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.IOException;

/**
 * ipfs操作工具类
 * @author LengYouNuan
 * @create 2021-05-28 下午3:14
 */
public class IpfsUtils {

    /**
     * ipfs服务器地址
     */
    private static final String url="/ip4/8.134.56.35/tcp/5001";

    private static IPFS ipfs = new IPFS(url);

    /**
     * ipfs上传文件操作
     * @param fileName  指定上传的文件名字
     * @return
     */
    public static java.lang.String ipfsUpload(String fileName) throws IOException {

        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper("hello_001.txt", "这是一个ipfs文件上传测试！！！".getBytes());
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash+"";
    }

    public static void get(String hashcode) throws IOException {
        Multihash filePointer = Multihash.fromBase58(hashcode);
        byte[] fileContents = ipfs.cat(filePointer);
        String string = new String(fileContents);
        System.out.println(string);
    }

    public static void main(String[] args) throws IOException {
        //String s = IpfsUtils.ipfsUpload("hello1.txt");
        //System.out.println(s);
        IpfsUtils.get("QmR4Us2VDtij5vLfBCyPYfyqf3ENMK4GWCFzXP1aASJUv6");
    }

}
