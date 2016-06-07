USE [Bodhi_lbms]
GO

/****** Object:  StoredProcedure [dbo].[sp_computeFines]    Script Date: 11/3/2014 8:27:42 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[sp_computeFines]
@status INT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	DECLARE @loan_id varchar(50);
	DECLARE @card_no int;
	DECLARE @no_of_days int;
	SET @status = 0;
	DECLARE fine_elig_cursor CURSOR FAST_FORWARD
	FOR select loan_id, 
			   card_no, 
			   datediff(day,due_date,ISNULL(date_in,GETDATE())) as no_of_days
     from Bodhi_lbms.dbo.book_loans 
	 where due_date < date_in 
	 or 
	 (due_date <  CONVERT (date, SYSDATETIME()) and date_in is NULL) 
	OPEN fine_elig_cursor
	FETCH NEXT FROM fine_elig_cursor INTO @loan_id,@card_no,@no_of_days
	WHILE @@FETCH_STATUS = 0
	BEGIN
	SET @status = @status + 1 ;
	IF EXISTS (SELECT * FROM Bodhi_lbms.dbo.fines WHERE loan_id = @loan_id)
        BEGIN
            UPDATE Bodhi_lbms.dbo.fines SET fine_amt = (@no_of_days*0.25) WHERE loan_id = @loan_id;
        END
    ELSE
        BEGIN
            INSERT INTO Bodhi_lbms.dbo.fines VALUES (@loan_id,(@no_of_days*0.25),1)
        END
		FETCH NEXT FROM fine_elig_cursor INTO @loan_id,@card_no,@no_of_days
		END
	CLOSE fine_elig_cursor;
	DEALLOCATE fine_elig_cursor;
	END



GO


