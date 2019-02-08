SELECT vote_number, COUNT(DISTINCT senator_ID) as num_votes FROM VoteCast
GROUP BY vote_number
ORDER BY num_votes

/*
 * There are a total of 9 missing votes, from Vote194 to Vote202
 * 
 * vote_number	num_votes
 * 194			99
 * 195			99
 * 196			99
 * 197			99
 * 198			99
 * 199			99
 * 200			99
 * 201			99
 * 202			99
 * 
 */
