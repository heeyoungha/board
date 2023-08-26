package board.common.dto;

public class HistoryResponse<E> {
    private Long timestamp;
    private String adminId;
    private String operation;
    private String requestMapping;
    private E entity;
}
