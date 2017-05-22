package wroclaw.webrest.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import wroclaw.webrest.utils.Utils;

@Entity
@Table(name = "history")
public class HistoryEntity {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * This variable is responsible for contain document build from XML's
	 * separator <b>lastBuildDate</b>
	 */
	@Column(name = "document_build_date")
	private String documentBuildDate;

	/**
	 * This variable is responsible for contain date of check document is
	 * current.
	 */
	@Column(name = "date")
	private String date;

	@Column
	@OneToMany(mappedBy = "history")
	private List<DocumentEntity> documents;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @see #documentBuildDate
	 * @return
	 */
	public String getDocumentBuildDate() {
		return documentBuildDate;
	}

	public void setDocumentBuildDate(String documentBuildDate) {
		this.documentBuildDate = documentBuildDate;
	}

	/**
	 * @see #date
	 * @return
	 */
	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date.toString();
	}

	public List<DocumentEntity> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentEntity> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "<h2>Encja historii: " + "</br>Id: " + id + "</br>Zbudowany: " + documentBuildDate
				+ "</br>Pobrany do bazy danych " + date + "</br></h2>" + Utils.convertListToString(documents);
	}

}
