package se.jasmin.exjobb.trainapp.service;

import se.jasmin.exjobb.trainapp.api.dto.AverageDto;
import se.jasmin.exjobb.trainapp.api.dto.AverageStat;
import se.jasmin.exjobb.trainapp.api.dto.ProgressDto;
import se.jasmin.exjobb.trainapp.api.dto.ProgressStat;

public interface StatService {
    ProgressStat getProgress(ProgressDto progressDto, Long userId, String id);

    AverageStat getAverage(AverageDto averageDto,Long userId, String id);


}
