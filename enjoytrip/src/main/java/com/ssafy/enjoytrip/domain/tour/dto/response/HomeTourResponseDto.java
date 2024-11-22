package com.ssafy.enjoytrip.domain.tour.dto.response;

import com.ssafy.enjoytrip.domain.member.dto.response.HomeMemberResponseDto;
import com.ssafy.enjoytrip.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HomeTourResponseDto {

    private Long id;

    private String tourName;

    private String address;

    private String backgroundImage;

    private Integer hit;

    // JPQL에서 사용할 생성자
    @Builder
    public HomeTourResponseDto(Long id, String tourName, String address,
                               String backgroundImage, Integer hit) {
        this.id = id;
        this.tourName = tourName;
        this.address = address;
        this.backgroundImage = backgroundImage;
        this.hit = hit;
    }

}