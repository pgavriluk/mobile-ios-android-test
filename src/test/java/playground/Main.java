package playground;

import enums.AccountType;
import enums.Airport;
import global.api.HeadersModel;
import global.api.MemberRequests;
import global.api.RestConfiguration;
import global.entity.Member;
import global.services.FastBookingService;
import org.junit.jupiter.api.Test;

public class Main {

    @Test
    public void test(){
        MemberRequests memberRequests = new MemberRequests();
        Member member = memberRequests.createNewMember(AccountType.CORPORATE);
        memberRequests.activateMember(member);
        memberRequests.resetNewMemberPassword(member);

        RestConfiguration restConfiguration = new RestConfiguration();
        HeadersModel headersModel = restConfiguration.configure(member);
        Airport fromAirport = Airport.KTEB;
        Airport toAirport = Airport.KBOS;

        FastBookingService fastBookingService = new FastBookingService();

        fastBookingService.bookFlight(member, fromAirport, toAirport, true);
        System.out.println("aaaa");
    }
}
