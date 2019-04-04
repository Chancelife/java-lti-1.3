package edu.uoc.elc.lti.platform.ags;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public interface AgsClient {

	/**
	 * Get the line items of the platform
	 * @param limit restricts the maximum number of items to be returned. If null doesn't apply this restriction.
	 * @param page indicates the offset for which this page should start containing items. Can be null.
	 * @param resourceLinkId limit the line items returned to only those which have been associated with the specified tool platform's resource link. Can be null.
	 * @param tag limit the line items returned to only those which have been associated with the specified tag. Can be null.
	 * @param resourceId limit the line items returned to only those which have been associated with the specified tool resource ID. Can be null.
	 * @return all the line items associated to tool in the specified learning context. Results may be broken in multiple pages in particular if a limit parameter is present.
	 */
	List<LineItem> getLineItems(Integer limit, Integer page, String resourceLinkId, String tag, String resourceId);

	/**
	 * Add a new line item; the created line item is returned with the url, scores and results endpoints (assuming the client has the enabled capabilities for those)
	 * @param lineItem the LineItem to create
	 * @return the LineItem created
	 */
	LineItem createLineItem(LineItem lineItem);

	/**
	 * Returns the current value for the line item.
	 * @param id the id of the line item.
	 * @return the line item of the given id
	 */
	LineItem getLineItem(String id);

	/**
	 * Updates the line item
	 * @param id Id of the line item.
	 * @param lineItem the updated line item.
	 * @return the updated line item.
	 */
	LineItem updateLineItem(String id, LineItem lineItem);

	/**
	 * Removes the line item. While no more associated with the tool, the tool platform may decide to keep the line item around (unassociated)
	 * @param id Id of the line item.
	 */
	void deleteLineItem(String id);

	/**
	 * Returns all the results for the line item (results for all the students in the current context for this line item) Results may be broken in multiple pages in particular if a limit parameter is present.
	 * @param id Id of the line item.
	 * @param limit restricts the maximum number of items to be returned. The response may be further constrained. If null doesn't apply this restriction.
	 * @param page indicates the offset for which this page should start containing items. Used exclusively by the nextPage URL. Can be null.
	 * @param userId limit the line items returned to only those which have been associated with this user. Results must contain at most one result.
	 * @return all the results for the line item
	 */
	List<Result> getLineItemResults(String id, Integer limit, Integer page, String userId);

	/**
	 * Publishes a score update. Tool platform may decide to change the result value based on the updated score.
	 * @param lineItemId Id of the line item
	 * @param score the score to update
	 * @return true if the score was updated. false otherwise
	 */
	boolean score(String lineItemId, Score score);
}
