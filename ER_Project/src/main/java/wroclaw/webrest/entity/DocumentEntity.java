package wroclaw.webrest.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class DocumentEntity {
	/**
	 * 
	 */
	@Id
	@Column(name = "document_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int documentId;
	/**
	 * 
	 */
	@Column
	private String link;
	/**
	 * 
	 */
	@Column(name = "date")
	private String date;
	/**
	 * 
	 */
	@Column
	@OneToMany(mappedBy = "document")
	private List<CurrencyEntity> currency;

	@JoinColumn(name = "from_which_update")
	@ManyToOne
	private HistoryEntity history;

	/**
	 * 
	 */
	@Column(name = "internal_code_of_document", unique = true)
	private String internalCode;

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	/**
	 * It's date of publish document. Format: yyyy-mm-dd
	 * 
	 * @return
	 */
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Return list of currency which are in document. </br>
	 * Usually about 25 currency.</br>
	 * Relation: OneToMany
	 * 
	 * @see OneToMany
	 * @see CurrencyEntity
	 * @return
	 */
	public List<CurrencyEntity> getCurrency() {
		return currency;
	}

	public void setCurrency(List<CurrencyEntity> currency) {
		this.currency = currency;
	}

	/**
	 * Return link to download document (*.XML).</br>
	 * 
	 * @return
	 */
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Return document internal code, e.g. 095/A/NBP/2017 </br>
	 * If you know document internal code, you should find it by code not id.
	 * 
	 * @return
	 */
	public String getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(String internalNo) {
		this.internalCode = internalNo;
	}

	public HistoryEntity getHistory() {
		return history;
	}

	public void setHistory(HistoryEntity history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "<a href=/document/"+documentId+"/show>"	+ "<div class='entry'> Informacje o dokumencie " + internalCode + "</a>: </br>" + "id: " + documentId + "</br> link: <a href="
				+ link + ">" + link + "</a></br>" + "date: " + date + "<br/> Z aktualizacji: " + history.getDate() + "</div>";
	}

}
