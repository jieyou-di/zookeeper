package com.zd.zk;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author 笛
 * @create 2021/8/11 9:27
 */
public class zkClient {
    //zk集群对应的地址
//    private static final String connerString ="192.168.1.109:2181,192.168.1.108:2181,192.168.1.107:2181";
//    private static final String connerString ="master:2181,node1:2181,node2:2181";
    private static final String connerString ="master:2181";

    //最长连接时间2000毫秒
    private static final int sessionTimeout=20000;
    private ZooKeeper zkClient;

//    public static void waitUntilConnected(ZooKeeper zooKeeper, CountDownLatch connectedLatch) {
//        if (ZooKeeper.States.CONNECTING == zooKeeper.getState()) {
//            try {
//                connectedLatch.await();
//            } catch (InterruptedException e) {
//                throw new IllegalStateException(e);
//            }
//        }
//    }
    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connerString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
    @Test
    public void create() throws KeeperException, InterruptedException {
//        while(true){
//            if (ZooKeeper.States.CONNECTING == zkClient.getState()) {
//                Thread.sleep(1000);
//            }else {
//                break;
//            }
//        }
//        System.setProperty("zookeeper.sasl.client", "false");

        Thread.sleep(5000);
        String nodeCreate = zkClient.create("/atguigu","ss.avi".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    }
}
