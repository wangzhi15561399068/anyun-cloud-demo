package test.com.anyun.cloud.demo.common.registry;

import com.anyun.cloud.demo.common.registry.utils.DeviceIdGenerator;
import com.anyun.cloud.demo.common.registry.utils.GsonUtil;
import com.anyun.cloud.demo.common.registry.constant.NodeInformationEntity;
import com.anyun.cloud.demo.common.registry.constant.NodeNetworkInfoEntity;
import com.anyun.cloud.demo.common.registry.constant.NodeType;
import com.anyun.common.lang.NetworkUtils;
import org.junit.Test;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/18
 */
public class JsonTest extends BaseRegistryTest {
    @Test
    public void test1() throws Exception {
        NodeInformationEntity entity = new NodeInformationEntity();
        entity.setNodeType(Arrays.asList(NodeType.API_REST_NODE, NodeType.SERVICE_NODE));
        entity.setTimestamp(System.currentTimeMillis());
        entity.setUid(DeviceIdGenerator.getGenerator().generate());
        entity.setUpstartTimestamp(entity.getTimestamp() - 10000);
        Map<String, List<InterfaceAddress>> networks = NetworkUtils.getInterfacesAddersses();
        for (Map.Entry<String, List<InterfaceAddress>> entry : networks.entrySet()) {
            NodeNetworkInfoEntity networkInfoEntity = new NodeNetworkInfoEntity();
            String name = entry.getKey();
            List<InterfaceAddress> addresses = entry.getValue();
            networkInfoEntity.setEtherName(name);
            List<String> ipList = new ArrayList<>();
            for (InterfaceAddress address : addresses) {
                ipList.add(address.getAddress().getHostAddress());
            }
            networkInfoEntity.setIp(ipList);
        }
        String json = GsonUtil.getUtil().toJson(entity);
        System.out.println(json);
    }
}
