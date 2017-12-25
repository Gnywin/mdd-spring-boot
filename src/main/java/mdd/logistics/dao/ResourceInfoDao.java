package mdd.logistics.dao;

import mdd.logistics.domain.ResourceInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceInfoDao {
    int insertList(@Param("list") List<ResourceInfo> resourceInfos,@Param("orderId") Long orderId);
}
