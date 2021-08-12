package com.zd.case1;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Description
 * @Author 笛
 * @create 2021/8/12 10:13
 */
public class DistributeServer {
    private String connectString="master:2181,node1:2181,node2:2181";
    private int sessionTiomeout=4000;
    private ZooKeeper zk;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer server = new DistributeServer();
        //1、获取zk连接
        server.getConnect();
        //2、注册服务器到zk
        server.regist(args[0]);
        //3、启动业务逻辑
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String hostname) throws KeeperException, InterruptedException {
        String creat = zk.create("/servers/"+hostname, hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+" is online");
    }

    private void getConnect() throws IOException {
        zk = new ZooKeeper(connectString, sessionTiomeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
