package board.member.type;

import javax.persistence.AttributeConverter;

public class Bookmark {

    private int bookmark;

    public Bookmark(int bookmark){
        if(bookmark < 0){
            throw new IllegalArgumentException("존재할 수 없는 카운트입니다");
        }
        this.bookmark = bookmark;
    }

    public Integer getBookmark(){
        return bookmark;
    }

    public static class BookmarkConverter implements AttributeConverter<Bookmark, Integer>{

        @Override
        public Integer convertToDatabaseColumn(Bookmark attribute) {
            return attribute.getBookmark();
        }

        @Override
        public Bookmark convertToEntityAttribute(Integer dbData) {
            return new Bookmark(dbData);
        }
    }

}
