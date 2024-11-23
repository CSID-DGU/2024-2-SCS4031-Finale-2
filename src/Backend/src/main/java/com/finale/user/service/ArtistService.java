package com.finale.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finale.global.exception.user.ResourceNotFoundException;
import com.finale.user.dto.ArtistInfoDto;
import com.finale.user.dto.BusinessArtistDto;
import com.finale.user.dto.StudentArtistDto;
import com.finale.user.dto.request.BusinessArtistReq;
import com.finale.user.dto.request.StudentArtistReq;
import com.finale.user.dto.response.ArtistDetailsRes;
import com.finale.user.entity.ArtistInfo;
import com.finale.user.entity.ArtistType;
import com.finale.user.entity.BusinessArtist;
import com.finale.user.entity.StudentArtist;
import com.finale.user.entity.User;
import com.finale.user.repository.ArtistInfoRepository;
import com.finale.user.repository.BusinessArtistRepository;
import com.finale.user.repository.StudentArtistRepository;
import com.finale.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ArtistService {
	private final UserRepository userRepository;
	private final BusinessArtistRepository businessArtistRepository;
	private final StudentArtistRepository studentArtistRepository;
	private final ArtistInfoRepository artistInfoRepository;
	@Transactional
	public void registerStudentsArtist(StudentArtistReq studentArtistReq, Long userId) {

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

		if (artistInfoRepository.existsByUserId(userId)) {
			throw new IllegalArgumentException("이미 등록된 아티스트입니다.");
		}

		// ArtistInfo 생성
		ArtistInfo artistInfo = ArtistInfo.builder()
			.userId(userId)
			.artistType(ArtistType.STUDENT)
			.nickname(user.getUserInfo().getNickname())
			.totalFollowers(0L)
			.totalLikes(0L)
			.about(studentArtistReq.about())
			.build();

		// StudentArtist 생성
		StudentArtist studentArtist = StudentArtist.builder()
			.schoolName(studentArtistReq.schoolName())
			.schoolEmail(studentArtistReq.schoolEmail())
			.major(studentArtistReq.major())
			.artistInfo(artistInfo)
			.build();

		studentArtistRepository.save(studentArtist);
	}

	@Transactional
	public void registerBusinessArtist(BusinessArtistReq businessArtistReq, Long userId) {

		User user = userRepository.getReferenceById(userId);

		if (artistInfoRepository.existsByUserId(userId)) {
			throw new DuplicateRequestException("이미 등록된 아티스트입니다.");
		}

		// BusinessArtist 생성
		ArtistInfo artistInfo = ArtistInfo.builder()
			.userId(userId)
			.artistType(ArtistType.BUSINESS)
			.nickname(user.getUserInfo().getNickname())
			.totalFollowers(0L)
			.totalLikes(0L)
			.about(businessArtistReq.about())
			.build();

		BusinessArtist businessArtist = BusinessArtist
			.builder()
			.businessNumber(businessArtistReq.businessNumber())
			.openDate(businessArtistReq.openDate())
			.headName(businessArtistReq.headName())
			.artistInfo(artistInfo)
			.build();

		businessArtistRepository.save(businessArtist);
	}

	@Transactional
	public ArtistDetailsRes getArtistDetails(Long userId) {
		ArtistInfo artistInfo = artistInfoRepository.findByUserId(userId)
			.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 아티스트입니다."));
		ArtistInfoDto artistInfoDto = ArtistInfoDto.fromEntity(artistInfo);

		switch (artistInfo.getArtistType()) {
			case STUDENT:
				StudentArtist studentArtist = studentArtistRepository.findByArtistInfo(artistInfo)
					.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 학생 아티스트입니다."));
				StudentArtistDto studentArtistDto = StudentArtistDto.from(studentArtist);
				return ArtistDetailsRes.from(artistInfoDto, studentArtistDto);
			case BUSINESS:
				BusinessArtist businessArtist = businessArtistRepository.findByArtistInfo(artistInfo)
					.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 사업자 아티스트입니다."));
				BusinessArtistDto businessArtistDto = BusinessArtistDto.from(businessArtist);
				return ArtistDetailsRes.from(artistInfoDto, businessArtistDto);
			default:
				throw new ResourceNotFoundException("존재하지 않는 아티스트입니다.");
		}
	}
}