package crawl;

import com.alibaba.fastjson.JSONObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeftTicketTranser {

    LeftTicketTranser INSTANCE = Mappers.getMapper(LeftTicketTranser.class);

    @Mapping(target = "", source = "")
    MyTicket fromJsonObject(JSONObject jsonObject);

}
