package com.ssafy.enjoytrip.domain.review.entity;

import com.ssafy.enjoytrip.domain.member.entity.Member;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class ReviewLikeId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Review review;  // ReviewLike 엔티티의 필드명과 정확히 일치
    private Member member;  // ReviewLike 엔티티의 필드명과 정확히 일치
}
