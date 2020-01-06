package com.ldy.util;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class ZkUtil {
    private ZooKeeper singleTonZooKeeper;
    private static int SESSION_TIME_OUT = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZkUtil() {
    }

    @PostConstruct
    public void createZkClient() {
        try {
            singleTonZooKeeper = new ZooKeeper("0.0.0.0:2181", SESSION_TIME_OUT, watcher);
            countDownLatch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getData(String path) {
        String result = null;
        try {
            result = new String(singleTonZooKeeper.getData(path, true, null));
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getChild(String path) {
        List<String> result = null;
        try {
            result = singleTonZooKeeper.getChildren(path, true);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                if (Watcher.Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) { // 连接时的监听事件
                    countDownLatch.countDown();
                } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) { // 子节点变更时的监听
                    try {
                        System.out.println("重新获得Children，并注册监听：" + singleTonZooKeeper.getChildren(watchedEvent.getPath(), true));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) { // 子节点变更时的监听
                    try {
                        System.out.println("重新获得data，并注册监听：" + new String(singleTonZooKeeper.getData(watchedEvent.getPath(), true, null)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
}