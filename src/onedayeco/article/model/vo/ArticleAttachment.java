package onedayeco.article.model.vo;

public class ArticleAttachment {

	private int attachment_id;
	private int article_id;
	private String original_filename;
	private String renamed_filename;
	private boolean attachment_status;
	public ArticleAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArticleAttachment(int attachment_id, int article_id, String original_filename, String renamed_filename,
			boolean attachment_status) {
		super();
		this.attachment_id = attachment_id;
		this.article_id = article_id;
		this.original_filename = original_filename;
		this.renamed_filename = renamed_filename;
		this.attachment_status = attachment_status;
	}
	public int getAttachment_id() {
		return attachment_id;
	}
	public void setAttachment_id(int attachment_id) {
		this.attachment_id = attachment_id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getOriginal_filename() {
		return original_filename;
	}
	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}
	public String getRenamed_filename() {
		return renamed_filename;
	}
	public void setRenamed_filename(String renamed_filename) {
		this.renamed_filename = renamed_filename;
	}
	public boolean isAttachment_status() {
		return attachment_status;
	}
	public void setAttachment_status(boolean attachment_status) {
		this.attachment_status = attachment_status;
	}
	@Override
	public String toString() {
		return "ArticleAttachment [attachment_id=" + attachment_id + ", article_id=" + article_id
				+ ", original_filename=" + original_filename + ", renamed_filename=" + renamed_filename
				+ ", attachment_status=" + attachment_status + "]";
	}
	
	
}
